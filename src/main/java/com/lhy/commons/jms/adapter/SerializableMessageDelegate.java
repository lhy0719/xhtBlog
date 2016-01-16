package com.lhy.commons.jms.adapter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.lhy.commons.jms.IMessageDelegate;


public abstract class SerializableMessageDelegate implements IMessageDelegate {
	private static Logger log = Logger.getLogger(SerializableMessageDelegate.class);
	@Override
	public void onReceivedMessage(String message) {
		log.error("received String message!");
	}
	@Override
	public void onReceivedMessage(@SuppressWarnings("rawtypes") Map message) {
		log.error("received Map message!");
	}
	@Override
	public void onReceivedMessage(byte[] message) {
		log.error("received byte[] message!");
	}
}
