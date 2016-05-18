<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>修改用户信息</title>
<#include "../html_meta.ftl" parse=true encoding="UTF-8">
<#include "../portal_headCSS.ftl" parse=true encoding="UTF-8">
<#include "../portal_headJS.ftl" parse=true encoding="UTF-8">
<script type="text/javascript" src="<@spring.url ''/>/ui/resources/js/user/modifyUserInfo.js"></script>
</head>
<body class="fs f12">
			<div class="login_page">
			<h1>用户信息修改</h1>
			<#if commonUser??>
		      <form id="modifyUserInfoForm" class="zcform" method="post">
		        <p>
		        <label for="commonUserAccount">用户名</label>
		        <input type="text" id="commonUserAccount" name="commonUserAccount" class="required" value="${commonUser.commonUserAccount}" style="border:none;background: transparent;box-shadow:none;font-weight: bold" disabled>
		        </p>
		        <p>
		        <label for="commonUserName">用户姓名</label>
		        <input type="text" id="commonUserName" name="commonUserName" class="required" value="${commonUser.commonUserName}"  autofocus>
		        </p>
		        <p>
		        <label for="commonUserPhone">联系电话</label>
		        <input type="text" id="commonUserPhone" name="commonUserPhone"  value="${commonUser.commonUserPhone}">
		        </p>
		        <p>
		        <label for="commonUserMail">电子邮件</label>
		        <input type="email" id="commonUserMail" name="commonUserMail" class="form-control" value="${commonUser.commonUserMail}">
		        </p>
		        <p>
		        <input class="submit" type="submit" value="确认修改"/>
		        </p>
		      </form>
		      </#if>
		    </div>
		<div class="messageBox"></div>
</body>
</html>
