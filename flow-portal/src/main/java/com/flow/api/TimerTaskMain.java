package com.flow.api;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

import com.flow.api.service.AbstractTimerTask;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.SpringBeanUtil;

/**
 * 定时任务
 * @author MK
 * @date 2016-08-06	
 */
public class TimerTaskMain {

	
	/**
	 * 每5分钟执行一次
	 * @throws Exception
	 */
	@Scheduled(cron = "0/15 * * * * ?")
	public void orderTask() throws Exception {
		Date fdate = new Date();
		System.out.println("=================test============");
		try {
				AbstractTimerTask orderTask = (AbstractTimerTask) SpringBeanUtil.getBean("orderTask");
				System.out.println("111");
				orderTask.call(fdate);
				System.out.println("222");
			} catch (Exception e) {
				System.out.println("出错了。" + e.getMessage());
				PubLog.info(e.getMessage());

			}
	}
	
	
	/**
	 * 每5分钟执行一次
	 * @throws Exception
	 */
	@Scheduled(cron = "0/45 * * * * ?")
	public void callBackTask() throws Exception {
		Date fdate = new Date();
		
		AbstractTimerTask orderTask = (AbstractTimerTask) SpringBeanUtil.getBean("callBackTask");
			try {

				orderTask.call(fdate);

			} catch (Exception e) {

				PubLog.info(e.getMessage());

			}
	}
	
}
