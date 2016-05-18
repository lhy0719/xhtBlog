<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>修改用户密码</title>
<#include "../html_meta.ftl" parse=true encoding="UTF-8">
<#include "../portal_headCSS.ftl" parse=true encoding="UTF-8">
<#include "../portal_headJS.ftl" parse=true encoding="UTF-8">encoding="UTF-8">
<script type="text/javascript" src="<@spring.url ''/>/ui/resources/js/user/modifyUserPsw.js"></script>
</head>
<body class="fs f12">
			<div class="login_page">
			<h1>用户密码修改</h1>
			<#if commonUser??>
		      <form id="modifyUserPswForm" class="zcform" method="post">
		      <input type="hidden" id="commonUserOID" name="commonUserOID" value="${commonUser.commonUserOID}" class="required">
		      	<p class="clearfix">
		        <label for="commonUserAccount" class="one">用户名</label>
		        <input type="text" id="commonUserAccount" name="commonUserAccount" class="required" value="${commonUser.commonUserAccount}" style="border:none;background: transparent;box-shadow:none;font-weight: bold" disabled>
		        </p>
		      	<p class="clearfix">
		        <label for="commonUserPassword" class="one">新密码</label>
		        <input type="password" id="commonUserPassword" name="commonUserPassword" class="required" autocomplete="off" autofocus>
		        </p>
		        <p class="clearfix">
		        <label for="recommonUserPassword" class="one">确认密码</label>
		        <input type="password" id="recommonUserPassword" name="recommonUserPassword"  autocomplete="off">
		        </p>
		        <p class="clearfix">
		        <input class="submit" type="submit" value="确认修改"></input>
		        </p>
		      </form>
		      </#if>
		    </div>
		<div class="messageBox"></div><!-- /addSuccessMessageBox -->
</body>
</html>
