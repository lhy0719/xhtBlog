package com.lhy.web;

public final class WebConstants {
	/**
	 * 当前已经登陆用户,类型:zxb.modules.system.domain.Person,字面值:{@value}
	 */
	public static final String LOGIN_USER_KEY="commonUser";
	/**
	 * 当前已经登陆用户具有的权限,类型:List&lt;zxb.modules.system.IPermissionDefProvider.Permission&gt;,字面值:{@value}
	 */
	public static final String LOGIN_Permission_KEY = "userPermissions";
	/**
	 * 当前已经登陆用户具有的权限名称,类型:List&lt;String&gt;,字面值:{@value}
	 */
	public static final String LOGIN_PermissionNames_KEY = "userPermissionsNames";
	/**
	 * 当前已经登陆用户所属用户组,类型:List&lt;zxb.modules.system.domain.UserGroup&gt;,字面值:{@value}
	 */
	public static final String LOGIN_UserGroups_KEY = "currentUser_userGroupList";
	/**
	 * 当前已经登陆用户的岗位,类型:Map&lt;String(zxb.modules.system.domain.UserGroup.userGroupOID),zxb.modules.system.domain.Quarters&gt;,字面值:{@value}
	 */
	public static final String LOGIN_QuartersMap_KEY = "currentUser_quartersMap";
	/**
	 * 当前已经登陆用户角色列表,类型:List&lt;String(zxb.modules.system.domain.RolePerson.roleName)&gt;,字面值:{@value}
	 */
	public static final String LOGIN_RoleNameList_KEY = "currentUser_roleNameList";
	/**
	 * 当前已经登陆用户ApplicationName列表,类型:List&lt;String(zxb.modules.system.IPermissionDefProvider.Application.applicationName)&gt;,字面值:{@value}
	 */
	public static final String LOGIN_ApplicationNameList_KEY = "currentUser_applicationNameList";	
	
	public static final class Pagination{
		public static final String Current_pageNum="currentPage";
		public static final String PageSize = "pageSize";
		public static final String TotalCount = "totalCount";
		public static final String TotalPageCount = "pageCount";
	}	
}