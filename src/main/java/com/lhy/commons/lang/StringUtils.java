package com.lhy.commons.lang;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {
	public static String formatMessage(String pattern,Object... arg){
		return MessageFormat.format(pattern, arg);
	}
	public static String encodeSHA512(String dataStr){
		return DigestUtils.sha512Hex(dataStr);
	}
	public static String encodeSHA256(String dataStr){
		return DigestUtils.sha256Hex(dataStr);
	}
}
