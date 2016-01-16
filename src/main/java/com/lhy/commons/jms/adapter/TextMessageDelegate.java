package com.lhy.commons.jms.adapter;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lhy.commons.jms.IMessageDelegate;

public abstract class TextMessageDelegate implements IMessageDelegate {
	private static Logger log = Logger.getLogger(TextMessageDelegate.class);
	public void onReceivedMessage(@SuppressWarnings("rawtypes") Map message) {
		log.error("received Map message!");
	}

	public void onReceivedMessage(byte[] message) {
		log.error("received byte[] message!");
	}

	public void onReceivedMessage(Serializable message) {
		log.error("received Serializable message!");
	}	
}
