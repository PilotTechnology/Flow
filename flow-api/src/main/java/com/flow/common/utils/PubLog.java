package com.flow.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubLog {
	static final Logger log = LoggerFactory.getLogger("PLATFORM");
	static final Logger errlog = LoggerFactory.getLogger("PLATFORMERROR");

	/**
	 * 输出调试信息
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @remark
	 */
	public static void debug(String message) {
		log.debug(getCaller() + message);
	}

	/**
	 * 输出调试信息，并且同时抛出异常
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @param arg1
	 *            要抛出的异常
	 * @remark
	 */
	public static void debug(String message, Throwable arg1) {
		log.debug(getCaller() + message, arg1);
	}

	/**
	 * 输出提示信息
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @remark
	 */
	public static void info(String message) {
		log.info(getCaller() + message);
	}

	/**
	 * 输出提示信息，并且同时抛出异常
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @param arg1
	 *            要抛出的异常
	 * @remark
	 */
	public static void info(String message, Throwable arg1) {
		log.info(getCaller() + message, arg1);
	}

	/**
	 * 输出警告信息
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @remark
	 */
	public static void warn(String message) {
		log.warn(getCaller() + message);
	}

	/**
	 * 输出警告信息，并且同时抛出异常
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @param arg1
	 *            要抛出的异常
	 * @remark
	 */
	public static void warn(String message, Throwable arg1) {
		log.warn(getCaller() + message, arg1);
	}

	/**
	 * 输出错误信息
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @remark 错误信息会被输出在日志文件和控制台中
	 */
	public static void error(String message) {
		errlog.error(getCaller() + message);
		log.error(getCaller() + message);
	}

	/**
	 * 输出错误信息，并且同时抛出异常
	 * 
	 * @param message
	 *            要输出的信息内容
	 * @param arg1
	 *            要抛出的异常
	 * @remark 错误信息会被输出在日志文件和控制台中
	 */
	public static void error(String message, Throwable arg1) {
		errlog.error(getCaller() + message, arg1);
		log.error(getCaller() + message, arg1);
	}

	/**
	 * 获取调用父类的名称
	 * 
	 * @return 调用父类的名称
	 */
	private static String getCaller() {
		String strRtn = "";
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		String className = stack[2].getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);
		strRtn = "( " + className + ", " + stack[2].getLineNumber();
		int tmpLength = strRtn.length();
		for (int i = 0; i < 35 - tmpLength; i++) {
			strRtn = strRtn + " ";
		}
		strRtn = strRtn + " ) ";
		return strRtn;
	}
}
