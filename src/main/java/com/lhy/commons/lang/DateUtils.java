package com.lhy.commons.lang;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.ParseException;
public class DateUtils {
	private static Logger log = Logger.getLogger(DateUtils.class);
	public static final String[] parsePatterns=new String[]{
		"yyyyMMddHHmmss",
		"yyyy-MM-dd HH:mm:ss",		
		"yyyyMMdd"
	};
	public static Date parseDate(String timeStr){
		Date returnTime=null;
		try {
			returnTime=StringUtils.isBlank(timeStr)?null:org.apache.commons.lang.time.DateUtils.parseDate(timeStr,parsePatterns);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return returnTime;
	}	
}
