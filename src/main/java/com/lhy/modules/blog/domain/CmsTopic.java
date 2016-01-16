package com.lhy.modules.blog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lhy.modules.system.SystemConstants;


@SuppressWarnings("serial")
@Entity(name="CmsTopic")
@Table(name="T_CmsTopic")
public class CmsTopic implements Serializable {
	public static final int TOPIC_NAME_LENGTH=20;
	public enum TopicType{text,image}
	private String topicOID;
	private String topicName;
	private String topicFullName;
	private String parentName;
	private Date createTime;
	private TopicType topicType;	
	@Id
	@Column(name = "topicOID", length =SystemConstants.HIBERNATE_UUID_LENGTH)
	@GeneratedValue(generator =SystemConstants.HEBERNATE_GENERATOR_UUID)
	@org.hibernate.annotations.GenericGenerator(name =SystemConstants.HEBERNATE_GENERATOR_UUID, strategy =SystemConstants.HEBERNATE_STRATEGY_UUID)
	public String getTopicOID() {
		return topicOID;
	}
	public void setTopicOID(String topicOID) {
		this.topicOID = topicOID;
	}
	@Column(name = "topicName", length =TOPIC_NAME_LENGTH)
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Column(name = "topicFullName", length =20)
	public String getTopicFullName() {
		return topicFullName;
	}
	public void setTopicFullName(String topicFullName) {
		this.topicFullName = topicFullName;
	}
	@Column(name = "parentName", length =TOPIC_NAME_LENGTH)
	@org.hibernate.annotations.Index(name="CmsTopic_index_parentName")
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Column(name = "createTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	@Column(name = "topicType",length =10)
	@Enumerated(EnumType.STRING)
	public TopicType getTopicType() {
		return topicType;
	}
	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}
}
