package com.lhy.modules.system.repository;

import java.util.List;

import com.lhy.modules.system.domain.SystemConfig;


public interface ISystemConfigPersistence {

	List<SystemConfig> findSystemConfigByName(String configName);

	String saveSystemConfig(SystemConfig systemConfig);

	void updateSystemConfig(SystemConfig systemConfig);

}
