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
	
}
