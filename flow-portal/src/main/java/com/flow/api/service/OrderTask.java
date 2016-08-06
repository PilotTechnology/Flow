package com.flow.api.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.flow.api.mapper.OrderApiMapper;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.DateUtil;
import com.flow.pub.util.MailUtils;
import com.flow.pub.util.PropertisUtil;
import com.flow.system.mapper.CostFlowMapper;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.model.CostFlow;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;

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
	private DistributorMapper distributorMapper;
	
	@Autowired
	private CostFlowMapper costFlowMapper;
	
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
			CostFlow costFlow = new CostFlow();
			for(Order order : orderList){
				//step 2 :查询下游报价单可订购流量包(获取最优折扣)
				//TODO
				
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
					
					//调用上游接口，成功发起扣费，失败返回
					String returnCode = callProviderApi();
					if(CodeConstants.SUCCESS.equals(returnCode)){

						//发起扣费
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
			}
		}
		
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
	 * 调用上游接口
	 * @return
	 */
	private String callProviderApi() {
		//TODO 上游接口调用实现
		return CodeConstants.SUCCESS;
	}
	
}
