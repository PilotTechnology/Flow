package com.flow.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.flow.api.http.HttpClientUtil;
import com.flow.api.mapper.OrderApiMapper;
import com.flow.pub.common.PubLog;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;
import com.flow.pub.common.CodeConstants;

/**
 * 定时通知下游接口
 * @author MK
 *
 */
@Service
public class CallBackTask extends AbstractTimerTask{

	@Autowired
	private OrderApiMapper orderApiMapper;
	
	private DistributorMapper distributorMapper;
	
	@Override
	protected void beforeExecute(Date fdate) throws Exception {
		
	}

	//定时通知
	@Override
	public void execute(Date fdate) throws Exception {
		List<Order> callBackList = orderApiMapper.getCallBackList();
		if(CollectionUtils.isEmpty(callBackList)){
			PubLog.info("当前没有需要通知的下游订单");
			return;
		}
		
		ExecutorService executors = Executors.newFixedThreadPool(callBackList.size());
		CountDownLatch countDownLatch = new CountDownLatch(callBackList.size());
		for(Order order : callBackList){
			try{
				//错误次数加1
				orderApiMapper.increaseNoticeErrorTime(order.getOrderCode());
				Distributor distributor = distributorMapper.selectByDistributorCode(order.getDistributorCode());
				String callbackUrl = distributor.getCallbackUrl();
				if (order.getState() == CodeConstants.ORDER_STATE_SUCC) {
					callbackUrl = callbackUrl + "?" + "code=0000";
				}else {
					callbackUrl = callbackUrl + "?" + "code=2001";
				}
				callbackUrl = callbackUrl + "&" + "order_id=" + order.getDistributorOrderCode();
				callbackUrl = callbackUrl + "&" + "p_order_id=" + order.getOrderCode();
				if (order.getState() == CodeConstants.ORDER_STATE_SUCC) {
					callbackUrl = callbackUrl + "&" + "msg=订购成功";
				}else {
					callbackUrl = callbackUrl + "&" + "msg=订购失败";
				}
				
				HttpPost httpPost = new HttpPost(callbackUrl);
				executors.execute(new DefaultRunnable(HttpClientUtil
						.getHttpClient(), httpPost, countDownLatch,order));
				
				countDownLatch.await();
				executors.shutdown();
			//更新通知结果
			}catch(Exception e){
				//更新通知结果
			}
		}
	}
	
	/**
	 * 上游接口异步响应
	 * @author MK
	 *
	 */
	class DefaultRunnable implements Runnable {
		private CountDownLatch countDownLatch;
		private final CloseableHttpClient httpClient;
		private final HttpPost httppost;
		private Order order;

		public DefaultRunnable(CloseableHttpClient httpClient, HttpPost httppost,
				CountDownLatch countDownLatch,Order order) {
			this.httpClient = httpClient;
			this.httppost = httppost;
			this.countDownLatch = countDownLatch;
			this.order = order;
		}

		@Override
		public void run() {
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(httppost,
						HttpClientContext.create());
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					EntityUtils.consume(entity);
					
					String result = EntityUtils.toString(entity);
					JSONObject obj = JSONObject.parseObject(result);
					if("0000".equals(obj.get("code"))){
						orderApiMapper.distributorCallbackSucc(order.getOrderCode());
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
				try {
					if (response != null)
						response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
