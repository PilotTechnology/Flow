package com.flow.pub.common;

public class CodeConstants {
	
	//状态码
	public static final String SUCCESS = "0000";				//成功
	public static final String SYS_ERR = "9999";				//系统错误
	
	//参数校验错误
	public static final String ARG_ERR_PHONE = "0001";			//手机号码参数缺失
	public static final String ARG_ERR_PRODUCT = "0002";		//流量包id参数缺失
	public static final String ARG_ERR_ORDER = "0003";			//订单号参数缺失
	public static final String ARG_ERR_TIME = "0004";			//时间戳参数缺失
	public static final String ARG_ERR_SIGN = "0005";			//签名参数缺失
	public static final String ARG_ERR_KEY = "0006";			//appkey参数缺失
	public static final String ARG_ERR_SCOPE = "0007";			//使用范围参数缺失
	public static final String ARG_ERR_PHONE_FORMAT = "0008";	//手机号码长度错误，应为11位
	public static final String ARG_ERR_PHONE_UNFOUND = "0009";	//手机号码无效
	public static final String ARG_ERR_SIGN_MISMATCH = "0010";	//签名不匹配
	public static final String ARG_ERR_OPERATOR = "0011";		//运营商参数缺失
	
	//账号错误
	public static final String ACC_ERR = "1001";				//账户异常
	public static final String ACC_ERR_BALANCE = "1002";		//余额不足
	public static final String ACC_ERR_IP = "1003";				//ip不在白名单
	public static final String ACC_ERR_NO_QUOTATION = "1004";	//未配置报价单
	public static final String ACC_ERR_IS_FORBIDDEN = "1005";	//账户被禁用
	public static final String ACC_ERR_NO_BALANCE = "1006"; 	//账户余额不足
	public static final String ACC_ERR_NO_PRODUCT = "1007"; 	//流量包未配置
	
	//订单状态
	public static final int ORDER_STATE_INIT = 0;				//处理中
	public static final int ORDER_STATE_SUCC = 1;				//充值成功
	public static final int ORDER_STATE_ERR  = 2;				//充值失败
	public static final int ORDER_STATE_SUBMIT_SUCC  = 3;		//提交供应商成功
	public static final int ORDER_STATE_SUBMIT_ERR   = 4;		//提交分销商失败
	
	//供应商退款状态
	public static final int PEOVIDER_NOT_REFUND = 0;			//未退
	public static final int PEOVIDER_IS_REFUNDED = 1;			//已退
	
	//用户状态
	public static final String USER_STATE_OFF = "0"	;			//禁用
	public static final String USER_STATE_ON = "1"	;			//激活
	
	//资金流水类型
	public static final int COST_FLOW_TYPE_KK = 0;				//扣款
	public static final int COST_FLOW_TYPE_REFUND = 1;			//退款
}
