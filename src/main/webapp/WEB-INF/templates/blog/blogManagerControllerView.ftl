<#import "/spring.ftl" as spring />
<#switch templateKey>
<#case "articleList">
	{
		"total":${totalCount?string.computer},
		"rows":[<#list articleList as article>{
			"articleOID":"${article.articleOID}",
			"topicName":"${article.topicName}",
			"title":"${article.title?js_string}",
			"author":"${article.author?js_string}",
			"createTime":"${article.createTime?string('yyyy-MM-dd hh:mm:ss')}"
		}<#if article_has_next>,</#if></#list>]
	}
<#break>

<#case "topicList">
	[<#list topicList as topic>{
		"topicOID":"${topic.topicOID}",
		"topicName":"${topic.topicName}",
		"topicType":"${topic.topicType}",
		"parentName":"<#if topic.parentName??>${topic.parentName}</#if>",
		"topicFullName":"${topic.topicFullName}",
		"id":"${topic.topicOID}_${.now?string('yyyy_MM_dd_hh_mm_ss')}",
		"text":"${topic.topicFullName}",
		"state":"closed"
	}<#if topic_has_next>,</#if></#list>]
<#break>

<#case "addOrUpdateCmsTopic">
	{    
	    "success": true,    
	    "message": "Message sent successfully."   
	}
<#break>

<#case "addOrUpdateCmsArticle">
	{    
	    "success": true,    
	    "message": "Message sent successfully."   
	}
<#break>

<#case "cmsTopic">
	{
		"topicOID":"${cmsTopic.topicOID}",
		"topicName":"${cmsTopic.topicName}",
		"topicType":"${cmsTopic.topicType}",
		"topicFullName":"${cmsTopic.topicFullName}",
		"parentName":"<#if cmsTopic.parentName??>${cmsTopic.parentName}</#if>",
		"createTime":"${cmsTopic.createTime?string('yyyy-MM-dd hh:mm:ss')}"
	}
<#break>

<#case "deleteCmsArticle">
	{"success":true}
<#break>

<#case "deleteCmsTopic">
	{"success":true}
<#break>

<#case "moveHelpCatalog">
	{"success":true}
<#break>

<#case "moveHelpContent">
	{"success":true}
<#break>

<#case "cmsArticle">
	{
		"articleOID":"${cmsArticle.articleOID}",
		"topicName":"${cmsArticle.topicName}",
		"title":"${cmsArticle.title?js_string}",
		"author":"${cmsArticle.author?js_string}",
		"content":"${cmsArticle.content?json_string}",
		"imageFileName":"<#if cmsArticle.imageFileName??>${cmsArticle.imageFileName?json_string}</#if>",
		"imageFilePath":"<#if cmsArticle.imageFilePath??>${cmsArticle.imageFilePath?json_string}</#if>",
		"imageContentType":"<#if cmsArticle.imageContentType??>${cmsArticle.imageContentType?json_string}</#if>",
		"createTime":"${cmsArticle.createTime?string('yyyy-MM-dd hh:mm:ss')}"
	}
<#break>

<#case "isTopicNameExist">
	{"isTopicNameExist":${isTopicNameExist?string}}
<#break>
</#switch>