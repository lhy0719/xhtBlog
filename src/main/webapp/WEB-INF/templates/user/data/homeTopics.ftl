{
	<#if indexTopicMap??>
		<#list indexTopicMap?keys as key>
			"${key}":[<#list indexTopicMap[key] as article>
				{"title":"${article.title}",
				"articleOID":"${article.articleOID}",
				"imageFileName":"${article.imageFileName}",
				"author":"${article.author}",
				"imageFilePath":"<#if article.imageFilePath??>${article.imageFilePath?json_string}</#if>",
				"createTime":"${article.createTime?string('yyyy-MM-dd')}",
				"content":"<#if article.content??>${article.content?json_string}</#if>"}<#if article_has_next>,</#if>
				</#list>]<#if key_has_next>,</#if>
		</#list>
	</#if>
}