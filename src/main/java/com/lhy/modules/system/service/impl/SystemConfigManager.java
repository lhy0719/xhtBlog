package com.lhy.modules.system.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhy.commons.CommonsConstants;
import com.lhy.commons.encrypt.IEncryptService;
import com.lhy.commons.encrypt.IEncryptService.License;
import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.SystemConfig;
import com.lhy.modules.system.repository.ISystemConfigPersistence;
import com.lhy.modules.system.service.ISystemConfigManager;

@Service(SystemConstants.CONFIG_SystemConfigManager_ID)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(readOnly = true, value = SystemConstants.CONFIG_TransactionManager_Name)
@Lazy(false)
public class SystemConfigManager implements ISystemConfigManager {
	private static Logger log=Logger.getLogger(SystemConfigManager.class);
	private static boolean isCopyRight=false;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['zxb.modules.system.impl.SystemConfigManager.licenseFile']}")
	private Resource licenseFile;
	@Value("#{"+SystemConstants.CONFIG_SystemConfigPersistence_ID+"}")
	private ISystemConfigPersistence systemConfigPersistence;
//	@Value("#{"+CommonsConstants.Commons_EncryptService_ID+"}")
//	private IEncryptService encryptService;
	@Override
	public SystemConfig getSystemConfigByName(String configName) {
		List<SystemConfig> configList=this.systemConfigPersistence.findSystemConfigByName(configName);
		return configList.isEmpty()?null:configList.get(0);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public String addSystemConfig(SystemConfig systemConfig) {
		return this.systemConfigPersistence.saveSystemConfig(systemConfig);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void updateSystemConfig(SystemConfig systemConfig) {
		this.systemConfigPersistence.updateSystemConfig(systemConfig);
	}
//	@PostConstruct
//	public void init(){
//		checkCopyRight();
//	}
	
//	@Scheduled(cron="0 59 23 * * ?")
//	public void checkCopyRight(){
//		try {
//			InetAddress addr= InetAddress.getLocalHost();
//			License license=encryptService.getLicense(licenseFile.getFile(), addr.getHostAddress());
//			switch (license.getLicenseType()){
//			case developer:				
//				Date stopTime=license.getStopTime();				
//				if (stopTime.after(new Date())){
//					isCopyRight=true;
//					log.info(MessageFormat.format("platform stopTime={0}", DateFormatUtils.format(stopTime, "yyyy-MM-dd")));
//				}else{
//					isCopyRight=false;
//				}
//				break;
//			case user:
//				isCopyRight=true;
//				break;
//			}
//		} catch (UnknownHostException e) {
//			log.error(e.getMessage());
//		} catch (IOException e) {
//			log.error(e.getMessage());
//		}		
//	}
//
//	@Override
//	public boolean getCopyRight() {
//		return isCopyRight;
//	}
}
