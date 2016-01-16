package com.lhy.modules.system.repository;

import java.util.List;

import com.lhy.modules.system.domain.CommonUser;


public interface ICommonUserPersistence {
	List<CommonUser> findCommonUserByAccount(String commonUserAccount);
	
	void addCommonUser(CommonUser commonUser);
	
	void updateCommonUser(CommonUser commonUser);

	CommonUser getCommonUserByOID(String commonUserOID);

	void deleteCommonUserByOID(String commonUserOID);

	List<CommonUser> queryCommonUser(CommonUser commonUser, int startLine, int rowCount);
	Long queryCommonUserCount(CommonUser commonUser);

}
