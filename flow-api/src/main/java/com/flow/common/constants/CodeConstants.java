package com.flow.common.constants;

public class CodeConstants {
	
	//状态码
	public static final String SUCCESS = "0000";			//成功
	public static final String SYS_ERR = "9999";			//系统错误
	
	//参数校验错误
	public static final String ARG_ERR_PHONE = "0001";		//手机号码错误
	public static final String ARG_ERR_PRODUCT = "0002";	//流量包ID错误
	public static final String ARG_ERR_ORDER = "0003";		//订单号错误
	public static final String ARG_ERR_TIME = "0004";		//时间戳错误
	public static final String ARG_ERR_SIGN = "0005";		//签名错误
	public static final String ARG_ERR_KEY = "0006";		//key错误
	
	//账号错误
	public static final String ACC_ERR = "1001";			//账户异常
	public static final String ACC_ERR_BALANCE = "1002";	//余额不足
	public static final String ACC_ERR_PIND = "1003";		//未绑定ID
	
	
	//订单状态
	public static final int ORDER_STATE_INIT = 0;			//处理中
	public static final int ORDER_STATE_SUCC = 1;			//处理成功
	public static final int ORDER_STATE_ERR  = 2;			//处理失败
	
}
