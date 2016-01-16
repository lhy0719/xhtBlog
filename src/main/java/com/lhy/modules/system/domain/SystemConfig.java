package com.lhy.modules.system.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lhy.modules.system.SystemConstants;

@SuppressWarnings("serial")
@Entity(name="SystemConfig")
@Table(name="T_SystemConfig")
public class SystemConfig implements Serializable {
	private String systemConfigOID;
	private String configName;
	private String configValue;
	private String configDescription;
	@Id
	@Column(name = "systemConfigOID", length =SystemConstants.HIBERNATE_UUID_LENGTH)
	@GeneratedValue(generator =SystemConstants.HEBERNATE_GENERATOR_UUID)
	@org.hibernate.annotations.GenericGenerator(name =SystemConstants.HEBERNATE_GENERATOR_UUID, strategy =SystemConstants.HEBERNATE_STRATEGY_UUID)
	public String getSystemConfigOID() {
		return systemConfigOID;
	}
	public void setSystemConfigOID(String systemConfigOID) {
		this.systemConfigOID = systemConfigOID;
	}
	@Column(name = "configName", length =50)
	@org.hibernate.annotations.Index(name="SystemConfig_index_configName")
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	@Column(name = "configValue", length =50)
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	@Column(name = "configDescription", length =200)
	public String getConfigDescription() {
		return configDescription;
	}
	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}
}
