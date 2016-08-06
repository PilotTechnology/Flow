package com.flow.api.service;

import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flow.pub.common.PubLog;


/**
 * 定时任务抽象类，所有业务执行类继承此类
 * @author MK
 * @date 2016-02-22
 */
public abstract class AbstractTimerTask {
	//任务名称
	protected String taskName;
	//任务描述
	protected String taskDesc;
	//异常消息
	protected String errorMeg;
	//批量处理大小
	protected final static int BATCH_SIZE = 1000; 

	@Transactional(propagation = Propagation.REQUIRED , readOnly = false)
	public void call(Date fdate) {
		try{
			
			PubLog.info(">>>["+taskDesc+"]任务执行开始.");
			
			//任务执行前处理
			beforeExecute(fdate);
			//任务执行
			execute(fdate);
			
			PubLog.info(">>>["+taskDesc+"]任务执行结束.");
		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 执行任务前处理，主要任务检查校验
	 * @param fdate
	 */
	protected abstract void beforeExecute(Date fdate) throws Exception;
	
	/**
	 * 执行任务
	 * @param fdate
	 */
	public abstract void execute(Date fdate) throws Exception;
	
}
