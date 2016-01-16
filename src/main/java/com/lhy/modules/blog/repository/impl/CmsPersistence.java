package com.lhy.modules.blog.repository.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lhy.commons.template.FreeMarkerProcessor;
import com.lhy.modules.blog.domain.CmsArticle;
import com.lhy.modules.blog.domain.CmsTopic;
import com.lhy.modules.blog.repository.ICmsPersistence;
import com.lhy.modules.system.SystemConstants;

@Repository(SystemConstants.CONFIG_CmsPersistence_ID)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Lazy(false)
public class CmsPersistence implements ICmsPersistence {
	private static final String templateKey="templateKey";
	@Value("#{"+SystemConstants.CONFIG_HibernateTemplate_ID+"}")
	private HibernateTemplate hibernateTemplate;
	@Value("#{"+SystemConstants.CONFIG_TemplateProcessor_ID+"}")
	private FreeMarkerProcessor freeMarkerProcessor;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['zxb.modules.cms.impl.CmsPersistence.cmsPersistenceTemplatePath']}")
	private String cmsPersistenceTemplatePath;
	@Override
	public List<CmsTopic> findTopicByParentName(final String parentName) {		
		return this.hibernateTemplate.execute(new HibernateCallback<List<CmsTopic>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<CmsTopic> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CmsTopic.class);
				if (parentName==null){
					criteria.add(Restrictions.isNull("parentName"));
				}else{
					criteria.add(Restrictions.eq("parentName", parentName));
				}
				criteria.addOrder(Order.asc("createTime"));
				return criteria.list();
			}});
	}
	@Override
	public String saveCmsTopic(CmsTopic cmsTopic) {		
		return (String)hibernateTemplate.save(cmsTopic);
	}
	@Override
	public CmsTopic getCmsTopic(String topicOID) {		
		return hibernateTemplate.get(CmsTopic.class, topicOID);
	}
	@Override
	public void updateCmsTopic(CmsTopic cmsTopic) {
		this.hibernateTemplate.update(cmsTopic);
	}
	@Override
	public Long getCmsArticleCount(final String topicName) {		
		return this.hibernateTemplate.execute(new HibernateCallback<Long>(){
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CmsArticle.class);
				criteria.add(Restrictions.eq("topicName", topicName));
				criteria.setProjection(Projections.rowCount());
				return (Long)criteria.list().get(0);
			}});
	}
	@Override
	public List<CmsArticle> findArticleByTopicName(final String topicName,
			final int startLine, final int rowCount) {
		return this.hibernateTemplate.execute(new HibernateCallback<List<CmsArticle>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<CmsArticle> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CmsArticle.class);
				criteria.add(Restrictions.eq("topicName", topicName));
				criteria.addOrder(Order.desc("createTime"));
				criteria.setFirstResult(startLine);
				criteria.setMaxResults(rowCount);
				return criteria.list();
			}});
	}
	@Override
	public String saveCmsArticle(CmsArticle cmsArticle) {		
		return (String)this.hibernateTemplate.save(cmsArticle);
	}
	@Override
	public void updateCmsArticle(CmsArticle cmsArticle) {
		this.hibernateTemplate.update(cmsArticle);
	}
	@Override
	public void deleteCmsArticle(String articleOID) {
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "deleteCmsArticle");
		final String hql=this.freeMarkerProcessor.process(this.cmsPersistenceTemplatePath, model);
		this.hibernateTemplate.bulkUpdate(hql, articleOID);
	}
	@Override
	public CmsArticle getCmsArticle(String articleOID) {		
		return this.hibernateTemplate.get(CmsArticle.class, articleOID);
	}
	@Override
	public void deleteCmsArticleByTopicName(String topicName) {
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "deleteCmsArticleByTopicName");
		final String hql=this.freeMarkerProcessor.process(this.cmsPersistenceTemplatePath, model);
		this.hibernateTemplate.bulkUpdate(hql, topicName);
	}
	@Override
	public void deleteCmsTopicByName(String topicName) {
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "deleteCmsTopicByName");
		final String hql=this.freeMarkerProcessor.process(this.cmsPersistenceTemplatePath, model);
		this.hibernateTemplate.bulkUpdate(hql, topicName);
	}
	@Override
	public Long getTopicNameCount(final String topicName) {
		return this.hibernateTemplate.execute(new HibernateCallback<Long>(){
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CmsTopic.class);
				criteria.add(Restrictions.eq("topicName", topicName));
				criteria.setProjection(Projections.rowCount());
				return (Long)criteria.list().get(0);
			}});
	}
	@Override
	public CmsTopic getCmsTopicByName(final String topicName) {
		return this.hibernateTemplate.execute(new HibernateCallback<CmsTopic>(){
			@Override
			public CmsTopic doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(CmsTopic.class);
				criteria.add(Restrictions.eq("topicName", topicName));
				@SuppressWarnings("rawtypes")
				List topicList=criteria.list();
				return topicList.isEmpty()?null:(CmsTopic)topicList.get(0);
			}});
	}
	@Override
	public List<CmsArticle> searchArticle(String queryParam, final int startLine,final int rowCount) {	
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "searchArticle");
		model.put("queryParam", queryParam);
		final String hql=this.freeMarkerProcessor.process(this.cmsPersistenceTemplatePath, model);
		return this.hibernateTemplate.execute(new HibernateCallback<List<CmsArticle>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<CmsArticle> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult(startLine);
				query.setMaxResults(rowCount);
				return query.list();
			}});
	}
	@Override
	public Long searchArticleCount(String queryParam) {
		Map<String,Object> model=new HashMap<String,Object>();
		model.put(templateKey, "searchArticleCount");
		model.put("queryParam", queryParam);
		final String hql=this.freeMarkerProcessor.process(this.cmsPersistenceTemplatePath, model);
		return this.hibernateTemplate.execute(new HibernateCallback<Long>(){
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				return (Long)query.list().get(0);
			}});
	}
}
