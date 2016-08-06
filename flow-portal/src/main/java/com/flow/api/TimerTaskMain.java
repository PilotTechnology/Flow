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
	 * 每日4点执行定时任务
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public void orderTask() throws Exception {
		Date fdate = new Date();
		
		AbstractTimerTask orderTask = (AbstractTimerTask) SpringBeanUtil.getBean("orderTask");
			try {

				orderTask.call(fdate);

			} catch (Exception e) {

				PubLog.info(e.getMessage());

			}
	}
	
	
	/**
	 * 每日4点执行定时任务
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 4 * * ?")
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
