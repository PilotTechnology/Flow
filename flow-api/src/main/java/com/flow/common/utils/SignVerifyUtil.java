package com.flow.common.utils;

import java.security.NoSuchAlgorithmException;

public class SignVerifyUtil {
	
	/**
	 * 订单接口sign验证
	 * 签名规则：MD5(phone=130xxxxxxxx&product_id=30&order_id=xxxxxxx&time=xxxxxx&secret=xxxxxxxx)
	 * @return boolean
	 */
	public static boolean orderApiSignVerify(String phone, String productId, String orderId, String time, String secret, String sign) {
		boolean timeIsVerified = SignVerifyUtil.verifyTime(time);
		if (timeIsVerified) {
			String str = "phone=" + phone + "&product_id=" + productId + "&order_id=" + orderId + "&time=" + time + "&secret=" + secret;
			try {
				String md5Str = MD5Util.EncodeString(str);
				if (md5Str.equals(sign)) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 订单查询接口sign验证
	 * 签名规则：MD5(order_id=xxxxxxx&time=xxxxxx&secret=xxxxxxxx)
	 * @return boolean
	 */
	public static boolean orderQueryApiSignVerify(String orderId, String time, String secret, String sign) {
		boolean timeIsVerified = SignVerifyUtil.verifyTime(time);
		if (timeIsVerified) {
			String str = "order_id=" + orderId + "&time=" + time + "&secret=" + secret;
			try {
				String md5Str = MD5Util.EncodeString(str);
				if (md5Str.equals(sign)) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 余额查询接口sign验证
	 * 签名规则：MD5(time=xxxxxx&secret=xxxxxxxx)
	 * @return boolean
	 */
	public static boolean balanceQueryApiSignVerify(String time, String secret, String sign) {
		boolean timeIsVerified = SignVerifyUtil.verifyTime(time);
		if (timeIsVerified) {
			String str = "time=" + time + "&secret=" + secret;
			try {
				String md5Str = MD5Util.EncodeString(str);
				if (md5Str.equals(sign)) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 验证时间戳是否有效，系统时间前后6小时有效
	 * @param time
	 * @return
	 */
	public static boolean verifyTime(String time) {
		long timestamp = 0;
		try
	    {
			timestamp = Long.parseLong(time);
			timestamp = timestamp/1000;
	    }
	    catch ( NumberFormatException e )
	    {
	    	return false;
	    }
		long currentTimestamp = System.currentTimeMillis()/1000;
		
		if (currentTimestamp - timestamp > 6 * 60 * 60 || currentTimestamp - timestamp < - 6 * 60 * 60) {
			return false;
		}else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(SignVerifyUtil.orderApiSignVerify("15611669213", "30", "123456", "1468725824489", "654321", "788e1b3d1d75062f36c091c531595773"));
		System.out.println(SignVerifyUtil.orderQueryApiSignVerify("123456", "1468725824489", "654321", "6244a9c6467c13095d520a7ae8538ff9"));
		System.out.println(SignVerifyUtil.balanceQueryApiSignVerify("1468725824489", "654321", "54ae26ce6b159183934cad5c622fe0ef"));
	}
}
