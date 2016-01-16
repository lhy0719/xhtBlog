package com.lhy.modules.blog.repository;

import java.util.List;

import com.lhy.modules.blog.domain.CmsArticle;
import com.lhy.modules.blog.domain.CmsTopic;


public interface ICmsPersistence {

	List<CmsTopic> findTopicByParentName(String parentName);

	String saveCmsTopic(CmsTopic cmsTopic);

	CmsTopic getCmsTopic(String topicOID);
	
	CmsTopic getCmsTopicByName(String topicName);

	void updateCmsTopic(CmsTopic cmsTopic);

	Long getCmsArticleCount(String topicName);

	List<CmsArticle> findArticleByTopicName(String topicName, int startLine, int rowCount);

	String saveCmsArticle(CmsArticle cmsArticle);

	void updateCmsArticle(CmsArticle cmsArticle);

	void deleteCmsArticle(String articleOID);

	CmsArticle getCmsArticle(String articleOID);

	void deleteCmsArticleByTopicName(String topicName);

	void deleteCmsTopicByName(String topicName);

	Long getTopicNameCount(String topicName);

	List<CmsArticle> searchArticle(String queryParam, int startLine, int rowCount);

	Long searchArticleCount(String queryParam);

}
