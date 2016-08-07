package com.flow.pub.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.flow.pub.common.PubLog;

public class PropertisUtil {

	private static Properties props ;
	
	public PropertisUtil(){}
	
	public static String getValue(String propName){
		if(props == null){
			props = new Properties();
			InputStream in = PropertisUtil.class.getResourceAsStream("/config.properties");
			try {
				props.load(in);
			} catch (IOException e) {
				PubLog.error("加载config.properties异常" + propName);
			}
		}
		return (String) props.get(propName);
	}
	
}
