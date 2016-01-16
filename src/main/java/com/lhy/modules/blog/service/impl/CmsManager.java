package com.lhy.modules.blog.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhy.modules.blog.domain.CmsArticle;
import com.lhy.modules.blog.domain.CmsTopic;
import com.lhy.modules.blog.repository.ICmsPersistence;
import com.lhy.modules.blog.service.ICmsManager;
import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.SystemConfig;
import com.lhy.modules.system.service.ISystemConfigManager;
import com.lhy.modules.blog.domain.CmsTopic.TopicType;
@Service(SystemConstants.CONFIG_CmsManager_ID)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(readOnly = true, value = SystemConstants.CONFIG_TransactionManager_Name)
@Lazy(false)
@CacheConfig(cacheNames={ICmsManager.CMS_CACHE_NAME})
public class CmsManager implements ICmsManager {
	public static final String Cms_SystemConfig_Name="isCmsInit";
	@Value("#{"+SystemConstants.CONFIG_CmsPersistence_ID+"}")
	private ICmsPersistence cmsPersistence;
	@Value("#{"+SystemConstants.CONFIG_SystemConfigManager_ID+"}")
	private ISystemConfigManager systemConfigManager;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.modules.system.impl.CmsManager.initCmsTopicResource']}")
	private Resource initCmsTopicResource;
	private void saveCmsTopicElement(Element element){
		CmsTopic cmsTopic=new CmsTopic();
		cmsTopic.setTopicName(element.attributeValue("topicName"));
		cmsTopic.setTopicFullName(element.attributeValue("topicFullName"));
		cmsTopic.setTopicType(TopicType.valueOf(element.attributeValue("topicType")));
		Element parent=element.getParent();
		cmsTopic.setParentName(parent.attributeValue("topicName"));
		cmsTopic.setCreateTime(new Date());
		this.cmsPersistence.saveCmsTopic(cmsTopic);
	}
	@SuppressWarnings("rawtypes")
	private List selectCmsTopicNodes() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
		File file = initCmsTopicResource.getFile();
		Document doc=reader.read(file);
		return doc.selectNodes("//cmsTopic");
	}
	@SuppressWarnings("rawtypes")
	@PostConstruct
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void init() throws IOException, DocumentException{
		SystemConfig systemConfig=this.systemConfigManager.getSystemConfigByName(Cms_SystemConfig_Name);
		if (systemConfig==null){			
			List nodes=selectCmsTopicNodes();
			for (Iterator ite = nodes.iterator(); ite.hasNext();) {				
				Element element=(Element)ite.next();
				saveCmsTopicElement(element);
	        }
			SystemConfig config=new SystemConfig();
			config.setConfigName(Cms_SystemConfig_Name);
			config.setConfigValue(Boolean.toString(true));
			this.systemConfigManager.addSystemConfig(config);
		}else if (!Boolean.valueOf(systemConfig.getConfigValue())){
			List nodes=selectCmsTopicNodes();
			for (Iterator ite = nodes.iterator(); ite.hasNext();) {				
				Element element=(Element)ite.next();
				String topicName=element.attributeValue("topicName");
				CmsTopic cmsTopic=this.cmsPersistence.getCmsTopicByName(topicName);
				if (cmsTopic!=null){
					cmsTopic.setTopicFullName(element.attributeValue("topicFullName"));
					this.cmsPersistence.updateCmsTopic(cmsTopic);
				}else{
					saveCmsTopicElement(element);
				}
	        }
			systemConfig.setConfigValue(Boolean.toString(true));
			this.systemConfigManager.updateSystemConfig(systemConfig);
		}
	}
	@Override
	@Cacheable(key="#root.methodName+#root.args[0]+#root.args[1]+#root.args[2]")
	public List<CmsArticle> findArticleByTopicName(String topicName,int pageNumber, int pageSize) {		
		int startLine=(pageNumber-1)*pageSize;
		return this.cmsPersistence.findArticleByTopicName(topicName,startLine,Integer.valueOf(pageSize));
	}

	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public List<CmsTopic> findChildTopic(String topicName) {
		String parentName=StringUtils.isBlank(topicName)?null:topicName.trim();
		return this.cmsPersistence.findTopicByParentName(parentName);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public String addCmsTopic(CmsTopic cmsTopic, String parentName) {
		cmsTopic.setCreateTime(new Date());
		cmsTopic.setParentName(StringUtils.isBlank(parentName)?null:parentName.trim());
		return this.cmsPersistence.saveCmsTopic(cmsTopic);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public void updateCmsTopic(CmsTopic cmsTopic) {
		CmsTopic dbTopic=this.cmsPersistence.getCmsTopic(cmsTopic.getTopicOID());
		dbTopic.setTopicFullName(cmsTopic.getTopicFullName());
		dbTopic.setTopicName(cmsTopic.getTopicName());
		this.cmsPersistence.updateCmsTopic(dbTopic);
	}

	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public CmsTopic getCmsTopic(String topicOID) {		
		return this.cmsPersistence.getCmsTopic(topicOID);
	}

	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public Long getCmsArticleCount(String topicName) {		
		return this.cmsPersistence.getCmsArticleCount(topicName);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public String addCmsArticle(CmsArticle cmsArticle, String topicName) {
		cmsArticle.setTopicName(StringUtils.isBlank(topicName)?null:topicName);
		cmsArticle.setCreateTime(new Date());
		return this.cmsPersistence.saveCmsArticle(cmsArticle);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public void updateCmsArticle(CmsArticle cmsArticle) {
		CmsArticle article=this.cmsPersistence.getCmsArticle(cmsArticle.getArticleOID());
		article.setContent(cmsArticle.getContent());
		article.setTitle(cmsArticle.getTitle());
		article.setAuthor(cmsArticle.getAuthor());
		article.setImageContentType(cmsArticle.getImageContentType());
		article.setImageFileName(cmsArticle.getImageFileName());
		article.setImageFilePath(cmsArticle.getImageFilePath());
		this.cmsPersistence.updateCmsArticle(article);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public void deleteCmsArticle(String[] articleOIDS) {
		for (String articleOID:articleOIDS){
			this.cmsPersistence.deleteCmsArticle(articleOID);
		}
	}

	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public CmsArticle getCmsArticle(String articleOID) {		
		return this.cmsPersistence.getCmsArticle(articleOID);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	@CacheEvict(allEntries=true,beforeInvocation=true)	
	public void deleteCmsTopic(String topicName) {
		List<CmsTopic> childTopicList=this.cmsPersistence.findTopicByParentName(topicName);
		for (CmsTopic cmsTopic:childTopicList){
			deleteCmsTopic(cmsTopic.getTopicName());
		}
		this.cmsPersistence.deleteCmsArticleByTopicName(topicName);
		this.cmsPersistence.deleteCmsTopicByName(topicName);
	}

	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public Boolean isTopicNameExist(String topicName) {
		Long count=this.cmsPersistence.getTopicNameCount(topicName);
		return count>0?true:false;
	}
	@Override
	@Cacheable(key="#root.methodName+#root.args[0].hashCode()")
	public Map<String, List<CmsArticle>> findIndexCmsArticles(Map<String, Integer> topicNames) {
		Map<String, List<CmsArticle>> indexTopicMap=new HashMap<String, List<CmsArticle>>();
		for (String topicName:topicNames.keySet()){
			int startLine=0;
			int rowCount=topicNames.get(topicName);
			List<CmsArticle> cmsArticles=this.cmsPersistence.findArticleByTopicName(topicName, startLine, rowCount);
			indexTopicMap.put(topicName, cmsArticles);
		}
		return indexTopicMap;
	}
	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public CmsTopic getCmsTopicByName(String topicName) {		
		return this.cmsPersistence.getCmsTopicByName(topicName);
	}
	@Override
	@Cacheable(key="#root.methodName+#root.args[0]+#root.args[1]+#root.args[2]")
	public List<CmsArticle> searchArticle(String queryParam, int pageNum,int pageSize) {
		int startLine=(pageNum-1)*pageSize;
		return this.cmsPersistence.searchArticle(queryParam,startLine,pageSize);
	}
	@Override
	@Cacheable(key="#root.methodName+#root.args[0]")
	public Long searchArticleCount(String queryParam) {		
		return this.cmsPersistence.searchArticleCount(queryParam);
	}
}
