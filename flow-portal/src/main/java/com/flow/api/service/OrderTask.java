package com.flow.api.service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.flow.api.mapper.OrderApiMapper;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.DateUtil;
import com.flow.system.mapper.DistributorMapper;
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
	DistributorMapper distributorMapper;
	
	@Override
	protected void beforeExecute(Date fdate) throws Exception {
		
	}

	//定时通知
	@Override
	public void execute(Date fdate){
		try {
			//step 1 :查询300个需要订购的订单
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
					Class<?> providerApi = Class.forName("com.flow.api.provider.test.TestApi");
					Object object = providerApi.newInstance();
					Class<?>[] param = new Class[3];
			        param[0] = Order.class;
			        param[1] = ExecutorService.class;
			        param[2] = CountDownLatch.class;
			        //获取Reflect的方法，第一个参数是方法名；第二个参数是参数的类型，注意是参数的类型！       
			        Method method=providerApi.getDeclaredMethod("order", Order.class,ExecutorService.class,CountDownLatch.class); 
			        method.invoke(object, order, executors, countDownLatch);
					
					countDownLatch.await();
					executors.shutdown();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
