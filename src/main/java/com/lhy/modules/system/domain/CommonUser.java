package com.lhy.modules.system.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lhy.modules.system.SystemConstants;



/**
 * 普通用户
 */
@SuppressWarnings("serial")
@Entity(name = "CommonUser")
@Table(name = "T_CommonUser")
public class CommonUser implements Serializable {
	private static final int CommonUser_accountAndpsw_LENGTH = 64;
	private static final int CommonUser_islocked_LENGTH = 16;
	
	//锁定状态
	public enum IsLocked{isLocked,notLocked}

	/**
	 * 用户OID
	 */
	private String commonUserOID;
	
	/**
	 * 用户注册名
	 */
	private String commonUserAccount;
	
	/**
	 * 用户密码
	 */
	private String commonUserPassword;

	/**
	 * 用户姓名
	 */
	private String commonUserName;
	
	/**
	 * 用户身份证ID
	 */
	private String commonUserIdentity;
	
	/**
	 * 用户联系电话
	 */
	private String commonUserPhone;
	
	/**
	 * 用户邮箱
	 */
	private String commonUserMail;
	
	/**
	 * 锁定状态
	 */
	private IsLocked isLocked;

	@Id
	@Column(name = "commonUserOID", length = SystemConstants.HIBERNATE_UUID_LENGTH)
	@GeneratedValue(generator = SystemConstants.HEBERNATE_GENERATOR_UUID)
	@org.hibernate.annotations.GenericGenerator(name = SystemConstants.HEBERNATE_GENERATOR_UUID, strategy = SystemConstants.HEBERNATE_STRATEGY_UUID)	
	public String getCommonUserOID() {
		return commonUserOID;
	}

	public void setCommonUserOID(String commonUserOID) {
		this.commonUserOID = commonUserOID;
	}

	@Column(name = "commonUserAccount", length = CommonUser_accountAndpsw_LENGTH)
	@org.hibernate.annotations.Index(name = "CommonUser_index_commonUserAccount")			
	public String getCommonUserAccount() {
		return commonUserAccount;
	}

	public void setCommonUserAccount(String commonUserAccount) {
		this.commonUserAccount = commonUserAccount;
	}

	@Column(name = "commonUserPassword", length = CommonUser_accountAndpsw_LENGTH)
	public String getCommonUserPassword() {
		return commonUserPassword;
	}

	public void setCommonUserPassword(String commonUserPassword) {
		this.commonUserPassword = commonUserPassword;
	}

	@Column(name = "commonUserName", length = 56)
	@org.hibernate.annotations.Index(name = "CommonUser_index_commonUserName")				
	public String getCommonUserName() {
		return commonUserName;
	}

	public void setCommonUserName(String commonUserName) {
		this.commonUserName = commonUserName;
	}
	
	@Column(name = "commonUserIdentity", length = 56)
	@org.hibernate.annotations.Index(name = "CommonUser_index_commonUserIdentity")					
	public String getCommonUserIdentity() {
		return commonUserIdentity;
	}

	public void setCommonUserIdentity(String commonUserIdentity) {
		this.commonUserIdentity = commonUserIdentity;
	}
	
	@Column(name = "commonUserPhone", length = 18)				
	public String getCommonUserPhone() {
		return commonUserPhone;
	}

	public void setCommonUserPhone(String commonUserPhone) {
		this.commonUserPhone = commonUserPhone;
	}

	@Column(name = "commonUserMail", length = 56)
	public String getCommonUserMail() {
		return commonUserMail;
	}

	public void setCommonUserMail(String commonUserMail) {
		this.commonUserMail = commonUserMail;
	}

	@Column(name = "isLocked", length = CommonUser_islocked_LENGTH)
	@Enumerated(EnumType.STRING)
	public IsLocked getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(IsLocked isLocked) {
		this.isLocked = isLocked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((commonUserAccount == null) ? 0 : commonUserAccount
						.hashCode());
		result = prime
				* result
				+ ((commonUserIdentity == null) ? 0 : commonUserIdentity
						.hashCode());
		result = prime * result
				+ ((commonUserMail == null) ? 0 : commonUserMail.hashCode());
		result = prime * result
				+ ((commonUserName == null) ? 0 : commonUserName.hashCode());
		result = prime * result
				+ ((commonUserOID == null) ? 0 : commonUserOID.hashCode());
		result = prime
				* result
				+ ((commonUserPassword == null) ? 0 : commonUserPassword
						.hashCode());
		result = prime * result
				+ ((commonUserPhone == null) ? 0 : commonUserPhone.hashCode());
		result = prime * result
				+ ((isLocked == null) ? 0 : isLocked.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommonUser other = (CommonUser) obj;
		if (commonUserAccount == null) {
			if (other.commonUserAccount != null)
				return false;
		} else if (!commonUserAccount.equals(other.commonUserAccount))
			return false;
		if (commonUserIdentity == null) {
			if (other.commonUserIdentity != null)
				return false;
		} else if (!commonUserIdentity.equals(other.commonUserIdentity))
			return false;
		if (commonUserMail == null) {
			if (other.commonUserMail != null)
				return false;
		} else if (!commonUserMail.equals(other.commonUserMail))
			return false;
		if (commonUserName == null) {
			if (other.commonUserName != null)
				return false;
		} else if (!commonUserName.equals(other.commonUserName))
			return false;
		if (commonUserOID == null) {
			if (other.commonUserOID != null)
				return false;
		} else if (!commonUserOID.equals(other.commonUserOID))
			return false;
		if (commonUserPassword == null) {
			if (other.commonUserPassword != null)
				return false;
		} else if (!commonUserPassword.equals(other.commonUserPassword))
			return false;
		if (commonUserPhone == null) {
			if (other.commonUserPhone != null)
				return false;
		} else if (!commonUserPhone.equals(other.commonUserPhone))
			return false;
		return isLocked == other.isLocked;
	}
	
}
