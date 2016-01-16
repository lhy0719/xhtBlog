package com.lhy.commons.spring;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>{
	private static Logger log = Logger.getLogger(SpringContextRefreshedEvent.class);
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info(DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss:SSS")+"->容器启动完毕");
	}

}
