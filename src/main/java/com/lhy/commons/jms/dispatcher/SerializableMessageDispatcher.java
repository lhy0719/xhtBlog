package com.lhy.commons.jms.dispatcher;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lhy.commons.jms.action.ISerializableMessageAction;
import com.lhy.commons.jms.adapter.SerializableMessageDelegate;

@Component("serializableMessageDispatcher")
public class SerializableMessageDispatcher extends SerializableMessageDelegate {
	private static Logger log = Logger.getLogger(SerializableMessageDispatcher.class);
	@SuppressWarnings("unused")
	private Map<String,ISerializableMessageAction> actionMap;
	@Value("#{flexActionMap}")
	public void setActionMap(Map<String, ISerializableMessageAction> actionMap) {
		this.actionMap = actionMap;
	}
	@Override
	public void onReceivedMessage(Serializable message) {
		log.info("服务消息："+message);
	}	
}
