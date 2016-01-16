package com.lhy.modules.blog.service;

import java.util.List;
import java.util.Map;

import com.lhy.modules.blog.domain.CmsArticle;
import com.lhy.modules.blog.domain.CmsTopic;


public interface ICmsManager {
	String CMS_CACHE_NAME="cmsCache";
	List<CmsArticle> findArticleByTopicName(String topicName, int pageNumber,
											int pageSize);

	List<CmsTopic> findChildTopic(String topicName);

	String addCmsTopic(CmsTopic cmsTopic, String parentName);

	void updateCmsTopic(CmsTopic cmsTopic);

	CmsTopic getCmsTopic(String topicOID);

	Long getCmsArticleCount(String topicName);

	String addCmsArticle(CmsArticle cmsArticle, String topicName);

	void updateCmsArticle(CmsArticle cmsArticle);

	void deleteCmsArticle(String[] articleOIDS);

	CmsArticle getCmsArticle(String articleOID);

	void deleteCmsTopic(String topicName);

	Boolean isTopicNameExist(String topicName);

	Map<String, List<CmsArticle>> findIndexCmsArticles(Map<String, Integer> topicNames);

	CmsTopic getCmsTopicByName(String topicName);

	List<CmsArticle> searchArticle(String queryParam, int pageNum, int pageSize);

	Long searchArticleCount(String queryParam);
	
}
