package com.lhy.modules.blog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lhy.modules.system.SystemConstants;

@SuppressWarnings("serial")
@Entity(name="CmsArticle")
@Table(name="T_CmsArticle")
public class CmsArticle implements Serializable {
	private String articleOID;
	private String topicName;
	private String title;
	private String author;
	private String content;
	private Date createTime;
	private String imageContentType;
	private String imageFileName;
	private String imageFilePath;
	@Id	
	@Column(name = "articleOID", length =SystemConstants.HIBERNATE_UUID_LENGTH)
	@GeneratedValue(generator =SystemConstants.HEBERNATE_GENERATOR_UUID)
	@org.hibernate.annotations.GenericGenerator(name =SystemConstants.HEBERNATE_GENERATOR_UUID, strategy =SystemConstants.HEBERNATE_STRATEGY_UUID)
	public String getArticleOID() {
		return articleOID;
	}
	public void setArticleOID(String articleOID) {
		this.articleOID = articleOID;
	}
	@Column(name = "topicName", length =CmsTopic.TOPIC_NAME_LENGTH)
	@org.hibernate.annotations.Index(name="CmsArticle_index_topicName")
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Column(name = "title", length =200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "content",length =1024000)
	@Lob
	@Basic(fetch=FetchType.EAGER,optional=true)
	@org.hibernate.annotations.Type(type=SystemConstants.CONFIG_ClobType_ClassName)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "createTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "author", length =50)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "imageContentType", length =100)
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	@Column(name = "imageFileName", length =100)
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	@Column(name = "imageFilePath", length =200)
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}	
}
