package com.lhy.modules.system.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CommonUserAspect {
	@AfterReturning(pointcut="execution(* com.lhy.modules.system.service.ICommonUserManager.commonUserLogin (..))&& args(commonUserAccount,commonUserPassword)",returning="loginResult")
	public void afterCommonUserLogin(String commonUserAccount,String commonUserPassword,String loginResult){
		System.out.println(commonUserAccount+"==="+commonUserPassword+"==="+loginResult);
	}
}
