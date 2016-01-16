/**                                
 * Copyright ® 2012 东软集团 政府事业部
 * 版权所有。     
 */ 

package com.lhy.commons.spring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;
import org.springframework.util.ResourceUtils;

/**
 * <p>Class       : neusoft.app.utils.SpringUtils
 * <p>Descdription: Spring工具
 *
 * @author  张小彬        Mail:zhang-xiaobin@neusoft.com
 * @version 1.0.0
 *<p>
 *--------------------------------------------------------------<br>
 * 修改履历：<br>
 *        <li> 2012-10-18，张小彬，创建文件<br>
 *--------------------------------------------------------------<br>
 *</p>
 */
public class SpringUtils {
	private static AbstractApplicationContext ctx;
	public static void initSpringContext(String springContextFileClassPath){
		if (ctx==null){
			ctx=new ClassPathXmlApplicationContext(ResourceUtils.CLASSPATH_URL_PREFIX+springContextFileClassPath);
		}
	}
	public static void shutdownSpringContext(){
		if (ctx!=null){
			ctx.registerShutdownHook();
			ctx.close();
			ctx=null;
		}
	}
	public static void initLogConsole(String workingDirSystemProperty,String logFileClassPath,long refreshInteval) throws FileNotFoundException {
		Log4jConfigurer.setWorkingDirSystemProperty(workingDirSystemProperty);
		Log4jConfigurer.initLogging(ResourceUtils.CLASSPATH_URL_PREFIX+logFileClassPath,refreshInteval);
	}
	private static void clearTextArea(JTextArea textArea){
		if (textArea.getLineCount()>200){
			textArea.setText("");
		}
	}
	
	/**
	 * <p>Method ：initLogConsole
	 * <p>Description : 控制台转向
	 *
	 * @param textArea 
	 * @author  张小彬         Mail:zhang-xiaobin@neusoft.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2012-10-18，zhang-xiaobin@neusoft.com，创建方法<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public static void initLogConsole(final JTextArea textArea){
		OutputStream textAreaStream = new OutputStream() {
			public void write(int b) throws IOException {
				clearTextArea(textArea);
				textArea.append(String.valueOf((char)b));
			}

			public void write(byte b[]) throws IOException {
				clearTextArea(textArea);
				textArea.append(new String(b));
			}

			public void write(byte b[], int off, int len) throws IOException {
				clearTextArea(textArea);
				textArea.append(new String(b, off, len));
			}
		}; 
		PrintStream myOut = new PrintStream(textAreaStream);
		System.setOut(myOut);
		System.setErr(myOut);
	}
	public static <T> T getServiceBean(String beanID,Class<T> type){
		if (ctx.containsBean(beanID)){
			return ctx.getBean(beanID, type);
		}else{
			return null;
		}		
	}
}
