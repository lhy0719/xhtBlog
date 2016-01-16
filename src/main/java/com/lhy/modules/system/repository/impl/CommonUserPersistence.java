package com.lhy.modules.system.repository.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lhy.commons.template.FreeMarkerProcessor;
import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.CommonUser;
import com.lhy.modules.system.repository.ICommonUserPersistence;



@Repository("commonUserPersistence")
public class CommonUserPersistence implements ICommonUserPersistence {
	private HibernateTemplate hibernateTemplate;
	private static final String templateKey="templateKey";
	
	@Value("#{"+SystemConstants.CONFIG_HibernateTemplate_ID+"}")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.modules.system.repository.impl.CommonUserPersistence.commonUserPersistenceTemplatePath']}")
	private String commonUserPersistenceTemplatePath;
	@Value("#{"+SystemConstants.CONFIG_TemplateProcessor_ID+"}")
	private FreeMarkerProcessor freeMarkerProcessor;

	@Override
	public List<CommonUser> findCommonUserByAccount(final String commonUserAccount) {
		return this.hibernateTemplate.execute(new HibernateCallback<List<CommonUser>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<CommonUser> doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CommonUser.class);
				criteria.add(Restrictions.eq("commonUserAccount", commonUserAccount));
				return criteria.list();
			}
		});
	}

	@Override
	public void addCommonUser(CommonUser commonUser){
		this.hibernateTemplate.save(commonUser);
	}
	
	@Override
	public void updateCommonUser(CommonUser commonUser){
		this.hibernateTemplate.update(commonUser);
	}

	@Override
	public CommonUser getCommonUserByOID(String commonUserOID){ 
		return hibernateTemplate.get(CommonUser.class, commonUserOID);
	}

	@Override
	public void deleteCommonUserByOID(String commonUserOID){
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "deleteCommonUserByOID");
		final String hql=this.freeMarkerProcessor.process(this.commonUserPersistenceTemplatePath, model);
		this.hibernateTemplate.bulkUpdate(hql, commonUserOID);
	}

	@Override
	public List<CommonUser> queryCommonUser(final CommonUser commonUser, final int startLine, final int rowCount) {
		return this.hibernateTemplate.execute(new HibernateCallback<List<CommonUser>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<CommonUser> doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CommonUser.class);
				if(commonUser!=null){
					criteria.add(getCommonUser(commonUser));
				}				
				criteria.setFirstResult(startLine);
				criteria.setMaxResults(rowCount);
				criteria.addOrder(Order.asc("commonUserOID"));
				return criteria.list();
			}
		});
	}

	private Example getCommonUser(CommonUser commonUser){
		return Example.create(commonUser).enableLike(MatchMode.ANYWHERE).ignoreCase();
	}

	@Override
	public Long queryCommonUserCount(final CommonUser commonUser){
		return this.hibernateTemplate.execute(new HibernateCallback<Long>(){
			@Override
			public Long doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CommonUser.class);
				if(commonUser!=null){
					criteria.add(getCommonUser(commonUser));
				}
				criteria.setProjection(Projections.rowCount());
				return (Long)criteria.list().get(0);
			}});
	}	
}
