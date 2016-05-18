<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>用户注册</title>
<#include "../html_meta.ftl" parse=true encoding="UTF-8">
<#include "../portal_headCSS.ftl" parse=true encoding="UTF-8">
<#include "../portal_headJS.ftl" parse=true encoding="UTF-8">
<script type="text/javascript" src="<@spring.url ''/>/ui/resources/js/user/register.js"></script>
<script type="text/javascript">
var webAppBasePath="<@spring.url ''/>";
</script>
</head>
<body class="fs f12">
			<!-- 最新公告 -->
			<div class="login_page">
			  <h1>用户注册</h1>
		      <form id="registerForm" class="zcform" method="post">
		        <p>
		        <label for="commonUserAccount" >
		        <input type="text" id="commonUserAccount" name="commonUserAccount"  class="required" placeholder="用户名" autofocus>
		        </label>
		        </p>
		        <p>
		        <label for="commonUserPassword" >
		        <input type="password" id="commonUserPassword" name="commonUserPassword" class="required" placeholder="密码" autocomplete="off">
		        </label>
		        </p>
		        <p>
		        <label for="recommonUserPassword" >
		        <input type="password" id="recommonUserPassword" name="recommonUserPassword" placeholder="密码确认" autocomplete="off">
		        </label>
		        </p>
		        <p>
		        <label for="kaptchaKey" >
		        <input class="required" type="text"  id="kaptchaKey" placeholder="验证码" name="kaptchaKey">
		        </label>
		        </p>
		        <p>
		        <label for="kaptchaImg">
		        <img alt="验证码" title="点击换一张" id="kaptchaImg" src="<@spring.url ''/>/user/Kaptcha.jpg" onclick="this.src=this.src+'?time='+Math.random()">
		        </label>
		        </p>
		        <p>
		        <label>
		        <input class="submit" type="submit" id="registerBtn" value="注册"></input>
		        </label>
		        </p>
		        <p>
		        <a href="<@spring.url ''/>/">返回首页</a>
				-
				<a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/login">返回登录页 →</a>
				</p>
		      </form>
		      <p class="alert alert-danger" id="warningbox"></p>
		    </div>
</body>
</html>
