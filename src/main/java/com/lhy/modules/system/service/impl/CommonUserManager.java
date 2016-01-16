package com.lhy.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhy.commons.lang.StringUtils;
import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.CommonUser;
import com.lhy.modules.system.domain.CommonUser.IsLocked;
import com.lhy.modules.system.repository.ICommonUserPersistence;
import com.lhy.modules.system.service.ICommonUserManager;


@Service("commonUserManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional(readOnly = true, value = SystemConstants.CONFIG_TransactionManager_Name)
@Lazy(false)
public class CommonUserManager implements ICommonUserManager {
	@Value("#{commonUserPersistence}")
	private ICommonUserPersistence commonUserPersistence;
	
	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public String registerCommonUser(CommonUser commonUser) {
		String registerResult = "";
		//检查用户Account是否存在
		CommonUser checkExistCommonUser = this.getCommonUserByAccount(commonUser.getCommonUserAccount());
		if(checkExistCommonUser == null){
			String psw =commonUser.getCommonUserPassword();
			commonUser.setIsLocked(IsLocked.notLocked);
			commonUser.setCommonUserPassword(StringUtils.encodeSHA256(psw));
			this.commonUserPersistence.addCommonUser(commonUser);
			registerResult = "registerSuccess";
		}else{
			registerResult = "acountRepetition";
		}
		return registerResult;
	}
	
	@Override
	public String commonUserLogin(String commonUserAccount, String commonUserPassword) {
		String loginResult = "";
		//检查用户Account是否存在
		CommonUser loginCommonUser = this.getCommonUserByAccount(commonUserAccount);
		if(loginCommonUser == null){
			loginResult = "userIsNotExist";
		}else{
			String psw =loginCommonUser.getCommonUserPassword();
			if(StringUtils.encodeSHA256(commonUserPassword).equals(psw)){//检查密码是否正确
				if(IsLocked.isLocked.equals(loginCommonUser.getIsLocked())){//检查用户状态是否锁定
					loginResult = "userIsLocked";
				}else{
					loginResult = "loginSuccess";
				}
			}else{
				loginResult = "pswIsWrong";
			}
		}
		return loginResult;
	}

	@Override
	public CommonUser getCommonUserByAccount(String commonUserAccount) {
		List<CommonUser> commonUserList = this.commonUserPersistence.findCommonUserByAccount(commonUserAccount);
		return commonUserList.isEmpty() ? null : commonUserList.get(0);
	}
	
	@Override
	public CommonUser getCommonUserByOID(String commonUserOID) {
		return this.commonUserPersistence.getCommonUserByOID(commonUserOID);
	}
	
	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void updateCommonUser(CommonUser commonUser) {
		CommonUser updateCommonUser = this.getCommonUserByAccount(commonUser.getCommonUserAccount());
		updateCommonUser.setCommonUserName(commonUser.getCommonUserName());
		updateCommonUser.setCommonUserIdentity(commonUser.getCommonUserIdentity());
		updateCommonUser.setCommonUserPhone(commonUser.getCommonUserPhone());
		updateCommonUser.setCommonUserMail(commonUser.getCommonUserMail());
		this.commonUserPersistence.updateCommonUser(updateCommonUser);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void lockOrUnLockCommonUser(String commonUserOID) {
		CommonUser updateCommonUser = this.getCommonUserByOID(commonUserOID);
		if(IsLocked.isLocked.equals(updateCommonUser.getIsLocked())){
			updateCommonUser.setIsLocked(IsLocked.notLocked);
		}else{
			updateCommonUser.setIsLocked(IsLocked.isLocked);
		}
		this.commonUserPersistence.updateCommonUser(updateCommonUser);
	}

	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void resetPsw(CommonUser commonUser) {
		CommonUser updateCommonUser = this.getCommonUserByOID(commonUser.getCommonUserOID());
		updateCommonUser.setCommonUserPassword(StringUtils.encodeSHA256(commonUser.getCommonUserPassword()));
		this.commonUserPersistence.updateCommonUser(updateCommonUser);
	}
	
	@Override
	@Transactional(readOnly = false, value = SystemConstants.CONFIG_TransactionManager_Name)
	public void deleteCommonUserByOID(String commonUserOID) {
		this.commonUserPersistence.deleteCommonUserByOID(commonUserOID);
	}

	@Override
	public List<CommonUser> queryCommonUser(CommonUser commonUser, String pageNum, String pageSize) {
		int startLine=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
		if ("".equals(commonUser.getCommonUserAccount())) {
			commonUser.setCommonUserAccount(null);
		}
		if ("".equals(commonUser.getCommonUserName())) {
			commonUser.setCommonUserName(null);
		}
		if ("".equals(commonUser.getCommonUserMail())) {
			commonUser.setCommonUserMail(null);
		}
		if ("".equals(commonUser.getCommonUserPhone())) {
			commonUser.setCommonUserPhone(null);
		}
		return this.commonUserPersistence.queryCommonUser(commonUser, startLine, Integer.valueOf(pageSize).intValue());
	}

	@Override
	public Long queryCommonUserCount(CommonUser commonUser) {
		if ("".equals(commonUser.getCommonUserAccount())) {
			commonUser.setCommonUserAccount(null);
		}
		if ("".equals(commonUser.getCommonUserName())) {
			commonUser.setCommonUserName(null);
		}
		if ("".equals(commonUser.getCommonUserMail())) {
			commonUser.setCommonUserMail(null);
		}
		if ("".equals(commonUser.getCommonUserPhone())) {
			commonUser.setCommonUserPhone(null);
		}
		return this.commonUserPersistence.queryCommonUserCount(commonUser);
	}
	public void testTask(){
		System.out.println("定时输出！");
	}
}
