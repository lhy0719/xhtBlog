package com.lhy.modules.system.service;

import java.util.List;

import com.lhy.modules.system.domain.CommonUser;


public interface ICommonUserManager {
	String registerCommonUser(CommonUser commonUser);
	
	String commonUserLogin(String commonUserAccount, String commonUserPassword);
		
	void updateCommonUser(CommonUser commonUser);
	void lockOrUnLockCommonUser(String commonUserOID);
	void resetPsw(CommonUser commonUser);
	
	void deleteCommonUserByOID(String commonUserOIDs);
	
	CommonUser getCommonUserByAccount(String commonUserAccount);
	CommonUser getCommonUserByOID(String commonUserOID);
	
	List<CommonUser> queryCommonUser(CommonUser commonUser, String pageNum, String pageSize);
	Long queryCommonUserCount(CommonUser commonUser);
}
