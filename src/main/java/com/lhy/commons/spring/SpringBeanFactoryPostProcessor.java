package com.lhy.commons.spring;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Lazy(false)
@Order(1)
public class SpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	private static Logger log = Logger.getLogger(SpringBeanFactoryPostProcessor.class);
	public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
			throws BeansException {
		log.info(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss:SSS")+"->开始初始化容器");
		if (log.isInfoEnabled()){
			Map<String,String> env=System.getenv();
			for (String key:env.keySet()){
				System.out.println(key+"_________"+env.get(key));
			}
			Properties properties=System.getProperties();//java.library.path
			for (Object key:properties.keySet()){
				System.out.println(key+"_________"+properties.getProperty((String)key));
			}	
		}
	}
}
