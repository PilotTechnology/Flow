package com.flow.api.provider.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.flow.api.http.HttpClientUtil;
import com.flow.api.util.OrderUtil;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.ProviderConstants;
import com.flow.pub.util.MD5Util;
import com.flow.system.model.Order;

public class TestApi {
	
	public static final String TEST_API_ORDER_URL = "http://localhost:8080/api/order.do";
	public static final String TEST_API_APPKEY = "123456";
	public static final String TEST_API_SECRET = "123456";

	public void order(Order order, ExecutorService executors, CountDownLatch countDownLatch) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		System.out.println("order...");
		
		HttpPost httpPost = new HttpPost(ProviderConstants.DEFAULT);
		//set param
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		String time = String.valueOf(System.currentTimeMillis());
		pairs.add(new BasicNameValuePair("appkey",TEST_API_APPKEY));
		pairs.add(new BasicNameValuePair("phone",order.getPhone()));
		pairs.add(new BasicNameValuePair("order_id",order.getOrderCode()));
		pairs.add(new BasicNameValuePair("product_id",String.valueOf(order.getSize())));
		pairs.add(new BasicNameValuePair("time",time));
		String sign = MD5Util.EncodeString(String.format("phone=%s&product_id=%s&order_id=%s&time=%d&secret=%s",
				order.getPhone(), String.valueOf(order.getSize()), order.getOrderCode(), time, TEST_API_SECRET));
		pairs.add(new BasicNameValuePair("sign",sign));
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
		
		executors.execute(new DefaultRunnable(HttpClientUtil
				.getHttpClient(), httpPost, countDownLatch,order));
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
			this.order = order;
			this.countDownLatch = countDownLatch;
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
					OrderUtil orderUtil = new OrderUtil();
					if("0000".equals(obj.get("code"))){
						orderUtil.updateOrder(CodeConstants.ORDER_STATE_SUBMIT_SUCC, obj.getString("order_id"), null, obj.getString("code"), obj.getString("msg"));
					}else {
						orderUtil.updateOrder(CodeConstants.ORDER_STATE_SUBMIT_ERR, obj.getString("order_id"), null, obj.getString("code"), obj.getString("msg"));
						orderUtil.refund(obj.getString("order_id"));
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
