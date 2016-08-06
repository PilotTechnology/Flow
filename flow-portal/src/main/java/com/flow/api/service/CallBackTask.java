package com.flow.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * 定时通知下游接口
 * @author MK
 *
 */
@Service
public class CallBackTask extends AbstractTimerTask{

	@Override
	protected void beforeExecute(Date fdate) throws Exception {
		
	}

	//定时通知
	@Override
	public void execute(Date fdate) throws Exception {
		//step 1 :查询100个需要通知的订单
		//step 2 :通知下游
		//step 3 :更新通知结果
	}

}
