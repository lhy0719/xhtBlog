package com.lhy.commons.jms.dispatcher;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.lhy.commons.jms.action.ITextMessageAction;
import com.lhy.commons.jms.adapter.TextMessageDelegate;

public abstract class TextMessageDispatcherTemplate extends TextMessageDelegate{
	private static Logger log = Logger.getLogger(TextMessageDispatcherTemplate.class);
	private Map<String,ITextMessageAction> actionMap;
	@Value("#{textMessageActionMap}")
	public void setActionMap(Map<String, ITextMessageAction> actionMap) {
		this.actionMap = actionMap;
	}
	protected abstract String getMessageKey(String message);
	@Override
	public void onReceivedMessage(String message) {
		String key=this.getMessageKey(message);
		if (StringUtils.isNotBlank(key)&&this.actionMap.containsKey(key)){
			this.actionMap.get(key).execute(message);
		}else{
			log.error(MessageFormat.format("action key:{0} or action not found!", key));
		}
	}

}
