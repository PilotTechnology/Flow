package com.flow.pub.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware{
	   private static ApplicationContext ctx;
	   
	   public SpringBeanUtil() {}
	   
	   public static Object getBean(String id){
		   if (ctx == null) {
			   throw new NullPointerException("ApplicationContext is null");
		   }
		   return ctx.getBean(id);
	   }
	   
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
				throws BeansException {
			ctx = applicationContext;
			
		 }
}
