package com.lhy.commons.spring;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;
@Component
public class SpringContextStoppedEvent implements ApplicationListener<ContextStoppedEvent>{
	private static Logger log = Logger.getLogger(SpringContextStoppedEvent.class);
	public void onApplicationEvent(ContextStoppedEvent event) {
		log.info(DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss:SSS")+"->容器已经停止");
	}

}
