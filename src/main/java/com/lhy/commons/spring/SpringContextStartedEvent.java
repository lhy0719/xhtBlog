package com.lhy.commons.spring;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
@Component
public class SpringContextStartedEvent implements ApplicationListener<ContextStartedEvent> {
	private static Logger log = Logger.getLogger(SpringContextStartedEvent.class);
	public void onApplicationEvent(ContextStartedEvent event) {
		log.info(DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss:SSS")+"->容器开始启动");
	}

}
