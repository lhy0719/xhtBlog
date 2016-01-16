package com.lhy.modules.system;

public final class SystemConstants {
	public enum Gender{male,female}
	public static final int HIBERNATE_UUID_LENGTH=32;
	public static final int USER_USERNAME_LENGTH=20;
	public static final String HEBERNATE_GENERATOR_UUID="hibernate-uuid";
	public static final String HEBERNATE_STRATEGY_UUID="uuid";
	public static final String CONFIG_TransactionManager_Name="hibernateTransactionManager";
	public static final String CONFIG_EmbedDataSource_TransactionManager_Name="embedHibernateTransactionManager";
	public static final String CONFIG_HibernateTemplate_ID="platformHibernateTemplate";
	public static final String CONFIG_Embed_HibernateTemplate_ID="embedHibernateTemplate";
	public static final String CONFIG_TemplateProcessor_ID = "templateProcessor";
	public static final String CONFIG_JmsTemplate_ID = "activeMQJmsTemplate";
	public static final String CONFIG_FtpUtils_ID = "ftpUtils";
	public static final String CONFIG_SysConfig_ID = "sysConfig";
	public static final String CONFIG_SuperUser_ID = "superUser";
	public static final String CONFIG_DeveloperUser_ID = "developerUser";	
	
	public static final String CONFIG_UserManager_ID="userManager";
	public static final String CONFIG_UserPersistence_ID="userPersistence";
	public static final String CONFIG_PermissionManager_ID="permissionManager";
	public static final String CONFIG_PermissonDefProvider_ID="platformPermissonDefProvider";
	public static final String CONFIG_PermissionPersistence_ID = "permissionPersistence";
	public static final String CONFIG_HelpManager_ID = "helpManager";	
	public static final String CONFIG_HelpPersistence_ID = "helpPersistence";	
	public static final String CONFIG_CmsManager_ID="cmsManager";
	public static final String CONFIG_CmsPersistence_ID="cmsPersistence";
	public static final String CONFIG_OnLineUserManager_ID = "onLineUserManager";
	public static final String CONFIG_OnLineUserPersistence_ID="onLineUserPersistence";
	public static final String CONFIG_SystemConfigManager_ID = "systemConfigManager";
	public static final String CONFIG_SystemConfigPersistence_ID = "systemConfigPersistence";	
	public static final String CONFIG_FileManager_ID = "fileManager";
	
	public static final String CONFIG_ClobType_ClassName = "org.springframework.orm.hibernate3.support.ClobStringType";
	
	public static final String CONFIG_userInterceptor_ID = "userInterceptor";	
}
