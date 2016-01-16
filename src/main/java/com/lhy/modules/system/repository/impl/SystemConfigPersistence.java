package com.lhy.modules.system.repository.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.SystemConfig;
import com.lhy.modules.system.repository.ISystemConfigPersistence;

@Repository(SystemConstants.CONFIG_SystemConfigPersistence_ID)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SystemConfigPersistence implements ISystemConfigPersistence {
	@Value("#{"+SystemConstants.CONFIG_HibernateTemplate_ID+"}")
	private HibernateTemplate hibernateTemplate;
	@Override
	public List<SystemConfig> findSystemConfigByName(final String configName) {		
		return this.hibernateTemplate.execute(new HibernateCallback<List<SystemConfig>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<SystemConfig> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(SystemConfig.class);
				criteria.add(Restrictions.eq("configName", configName));
				return criteria.list();
			}});
	}

	@Override
	public String saveSystemConfig(SystemConfig systemConfig) {		
		return (String)this.hibernateTemplate.save(systemConfig);
	}

	@Override
	public void updateSystemConfig(SystemConfig systemConfig) {
		this.hibernateTemplate.update(systemConfig);
	}

}
