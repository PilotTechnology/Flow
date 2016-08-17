package com.flow.pub.common;

/**
 * 统一的代码生成
 * @author MK
 *
 */
public class KeyGenerate {
	
	/**
	 * 编号MY + 系统纳秒数
	 * @return
	 */
	public static String getOrderCode(){
		return "MY" + System.nanoTime();
	}
	
	/**
	 * 生成报价单的业务主键
	 * @param distributorCode
	 * @return
	 */
	public static String getServiceCode(String distributorCode){
		return "SC_" + distributorCode + "_" + System.currentTimeMillis();
	}
}
