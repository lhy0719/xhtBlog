package com.lhy.commons.jms;

import java.io.Serializable;
import java.util.Map;

public interface IMessageDelegate {
	void onReceivedMessage(String message);
	void onReceivedMessage(@SuppressWarnings("rawtypes") Map message);
	void onReceivedMessage(byte[] message);
	void onReceivedMessage(Serializable message);
}
