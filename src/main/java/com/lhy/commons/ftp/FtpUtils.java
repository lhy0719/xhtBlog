package com.lhy.commons.ftp;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component("ftpUtils")
@Lazy(true)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class FtpUtils {
	
	private String address;	
	private int port;	
	private String userName;	
	private String userPwd;	
	private String controlEncoding;	
	private String textFileEncoding;
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 * eg:192.168.40.189
	 */
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.address']}")
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPort() {
		return port;
	}
	/**
	 * @param port
	 * eg:21
	 */
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.port']}")
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.userName']}")
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.userPwd']}")
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getControlEncoding() {
		return controlEncoding;
	}
	/**
	 * @param controlEncoding
	 * eg:UTF-8
	 */
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.controlEncoding']}")
	public void setControlEncoding(String controlEncoding) {
		this.controlEncoding = controlEncoding;
	}
	public String getTextFileEncoding() {
		return textFileEncoding;
	}
	/**
	 * @param textFileEncoding
	 * eg:UTF-8
	 */
	@Value("#{sysConfig['neusoft.module.cdm.utils.FtpUtils.textFileEncoding']}")
	public void setTextFileEncoding(String textFileEncoding) {
		this.textFileEncoding = textFileEncoding;
	}
	public void sendTextFileToServer(String fileText,String fileName) throws IOException{
		FTPClient ftp= new FTPClient();
		ftp.setControlEncoding(controlEncoding);//设置复制到FTP的字符集
		ftp.connect(address, port);//连接到FTP服务器上
		ftp.login(userName, userPwd);//登录FTP服务器
		InputStream inputFile =IOUtils.toInputStream(fileText,textFileEncoding);
		ftp.appendFile(fileName, inputFile);//将文件复制到FTP服务器上，第一个参数文文件名，第二个参数为文件流
		inputFile.close();
		ftp.logout();//退出登录
		ftp.disconnect();//断开连接
	}
}
