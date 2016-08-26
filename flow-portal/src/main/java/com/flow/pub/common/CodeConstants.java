package com.flow.pub.common;

public class CodeConstants {
	
	//状态码
	public static final String SUCCESS = "0000";				//成功
	public static final String SYS_ERR = "9999";				//系统错误
	
	//参数校验错误
	public static final String ARG_ERR_PHONE = "0001";			//手机号码错误
	public static final String ARG_ERR_PRODUCT = "0002";		//流量包ID错误
	public static final String ARG_ERR_ORDER = "0003";			//订单号错误
	public static final String ARG_ERR_TIME = "0004";			//时间戳错误
	public static final String ARG_ERR_SIGN = "0005";			//签名错误
	public static final String ARG_ERR_KEY = "0006";			//key错误
	public static final String ARG_ERR_SCOPE = "0007";			//key错误
	public static final String ARG_ERR_PHONE_FORMAT = "0008";	//手机号码长度错误
	public static final String ARG_ERR_PHONE_UNFOUND = "0009";	//手机号码错误
	
	//账号错误
	public static final String ACC_ERR = "1001";				//账户异常
	public static final String ACC_ERR_BALANCE = "1002";		//余额不足
	public static final String ACC_ERR_PIND = "1003";			//未绑定ID
	public static final String ACC_ERR_NO_QUOTATION = "1004";	//未配置报价单
	public static final String ACC_ERR_IS_FORBIDDEN = "1005";	//账户被禁用
	public static final String ACC_ERR_NO_BALANCE = "1006"; 	//账户余额不足
	public static final String ACC_ERR_NO_PRODUCT = "1007"; 	//流量包未配置
	
	
	//订单状态
	public static final int ORDER_STATE_INIT = 0;				//处理中
	public static final int ORDER_STATE_SUCC = 1;				//处理成功
	public static final int ORDER_STATE_ERR  = 2;				//处理失败
	
	//用户状态
	public static final String USER_STATE_OFF = "0"	;			//禁用
	public static final String USER_STATE_ON = "1"	;			//激活
	
	//资金流水类型
	public static final int COST_FLOW_TYPE_KK = 0;				//扣款
	public static final int COST_FLOW_TYPE_REFUND = 1;			//退款
}
