package com.lhy.commons.spring;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
@Component
public class SpringContextClosedEvent implements ApplicationListener<ContextClosedEvent>{
	private static Logger log = Logger.getLogger(SpringContextClosedEvent.class);
	public void onApplicationEvent(ContextClosedEvent event) {
		log.info(DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss:SSS")+"->容器开始关闭");
	}

}
