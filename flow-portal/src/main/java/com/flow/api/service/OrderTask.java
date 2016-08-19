package com.flow.api.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.flow.api.http.HttpClientUtil;
import com.flow.api.mapper.OrderApiMapper;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.ProviderConstants;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.DateUtil;
import com.flow.pub.util.MailUtils;
import com.flow.pub.util.PropertisUtil;
import com.flow.system.mapper.CostFlowMapper;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.ProductMapper;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.CostFlow;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;
import com.flow.system.model.Product;
import com.flow.system.model.Quotation;

/**
 * 定时通知上游接口
 * @author MK
 *
 */
@Service
public class OrderTask extends AbstractTimerTask{

	@Autowired
	private OrderApiMapper orderApiMapper;
	
	@Autowired
	DistributorMapper distributorMapper;
	
	@Autowired
	private CostFlowMapper costFlowMapper;
	
	@Autowired
	private QuotationMapper quotationMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	protected void beforeExecute(Date fdate) throws Exception {
		
	}

	//定时通知
	@Override
	public void execute(Date fdate) throws Exception {
		//获取下游列表
		List<Distributor> distributorList = distributorMapper.getAllEnableList();
		Map<String,Distributor> distributorMap = new HashMap<>();
		if(CollectionUtils.isEmpty(distributorList)){
			PubLog.error("当前没有可用下游!");
			return;
		}
		for(Distributor distributor : distributorList){
			distributorMap.put(distributor.getDistrbutorCode(), distributor);
		}
		
		//step 1 :查询300个需要通知的订单
		List<Order> orderList = orderApiMapper.getOrderList();
		
		if(CollectionUtils.isEmpty(orderList)){
			PubLog.warn("当前没有需要通知的订单>>> :" + DateUtil.getTimeofDate(fdate));
			return;
		}else{
			PubLog.info("当前需要通知的订单共计：" + orderList.size() + ">>> :" + DateUtil.getTimeofDate(fdate));
			
			//发起调用
			ExecutorService executors = Executors.newFixedThreadPool(orderList.size());
			CountDownLatch countDownLatch = new CountDownLatch(orderList.size());
			for(Order order : orderList){
				//step 2 :查询下游报价单可订购流量包(获取最优折扣)
				PubLog.info("Order info : " + order);
				Product product = getProductByOrder(order); 
				
				//step 3 :扣费，记录日志
				Distributor distributor = distributorMap.get(order.getDistributorCode());
				if(distributor!=null){
					if(distributor.getBalance() < 5 && //下游账户余额低于5元 拒绝交易
							(distributor.getBalance() - order.getRealPurchased().doubleValue())<0){
						PubLog.warn("下游：" + distributor.getCompany() + ": 余额已不足！！");
						distributorMap.remove(distributor);  
						continue;
					}else if(distributor.getBalance() > 100 && //余额低于100时 (只发一次邮件)
							(distributor.getBalance() - order.getRealPurchased().doubleValue()) < 100){
						//邮件告警
						sendMail(distributor);
					}
					
					//step 4 调用上游订购接口
					if(product!=null){
						HttpPost httpPost = new HttpPost(ProviderConstants.DEFAULT);
						//set param
						executors.execute(new DefaultRunnable(HttpClientUtil
								.getHttpClient(), httpPost, countDownLatch,order,distributor));
					}else{
						PubLog.error("该order没有找到产品，订购失败>>>>：" + order.getOrderCode());
					}
				}
			
				countDownLatch.await();
				executors.shutdown();
				}
			}
		}
	
	/**
	 * 根据订单获取最佳折扣
	 * @param order
	 * @return
	 */
	private Product getProductByOrder(Order order) {
		//根据分销商获取service报价单
		Quotation quotation = quotationMapper.getQuotationByDistributorCode(order.getDistributorCode());
		//根据报价单和订单信息获取最优折扣的流量包
		Product pro = productMapper.findProductByService(quotation , order);
		return pro;
	}

	/**
	 * 发送告警邮件
	 * @param distributor
	 */
	private void sendMail(Distributor distributor) {
		String smtpServer=PropertisUtil.getValue("smtpServer").trim();
		String userName=PropertisUtil.getValue("userName").trim();
		String passWord=PropertisUtil.getValue("passWord").trim();
		String fromMail=PropertisUtil.getValue("fromMail").trim();
		String mailTo= distributor.getUser().getEmail();
		String mailSubject="【**】流量平台余额告警";
		StringBuilder mailContent=new StringBuilder(distributor.getUser().getNickname()+"， 您好！：");
		mailContent.append("\n");
		mailContent.append("\t\t 您当前的平台账户余额为：" + distributor.getBalance() + "元， 请注意及时充值！");

		try {
			boolean flag=MailUtils.sendMails(smtpServer, userName, passWord, fromMail, mailTo, mailSubject, mailContent.toString(), null);
			if(flag){
				PubLog.info("邮件发送成功!");
			}
		} catch (UnsupportedEncodingException e1) {
			PubLog.error("邮件发送失败!");
			e1.printStackTrace();
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
		private Distributor distributor; 

		public DefaultRunnable(CloseableHttpClient httpClient, HttpPost httppost,
				CountDownLatch countDownLatch,Order order,Distributor distributor) {
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
					if(CodeConstants.SUCCESS.equals(obj.get("result"))){
						//发起扣费
						CostFlow costFlow = new CostFlow();
						BeanUtils.copyProperties(order, costFlow);
						costFlow.setCost(order.getRealPurchased().doubleValue());
						costFlow.setCurrentBalance(distributor.getBalance());
						costFlow.setType(CodeConstants.COST_FLOW_TYPE_KK);
						costFlowMapper.insert(costFlow);
						
						//修改下游余额
						distributor.setBalance(distributor.getBalance() - order.getRealPurchased().doubleValue());
						distributorMapper.updateByPrimaryKey(distributor);
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
