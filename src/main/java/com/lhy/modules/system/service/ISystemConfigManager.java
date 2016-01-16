package com.lhy.modules.system.service;

import com.lhy.modules.system.domain.SystemConfig;


public interface ISystemConfigManager {
	SystemConfig getSystemConfigByName(String configName);

	String addSystemConfig(SystemConfig systemConfig);

	void updateSystemConfig(SystemConfig systemConfig);

//	public boolean getCopyRight();
}
