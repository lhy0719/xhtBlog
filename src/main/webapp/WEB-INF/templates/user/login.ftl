<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>用户登录</title>
<#include "../html_meta.ftl" parse=true encoding="UTF-8">
<#include "../portal_headCSS.ftl" parse=true encoding="UTF-8">
<#include "../portal_headJS.ftl" parse=true encoding="UTF-8">
<script type="text/javascript"
	src="<@spring.url ''/>/ui/resources/js/user/login.js"></script>
<script type="text/javascript" src="<@spring.url ''/>/thirdlib/rsa/Barrett.js"></script>
<script type="text/javascript" src="<@spring.url ''/>/thirdlib/rsa/BigInt.js"></script>
<script type="text/javascript" src="<@spring.url ''/>/thirdlib/rsa/RSA.js"></script>
</head>
<body class="f12">
	<!-- 最新公告 -->
	<div class="login_page">
		<h1>用户登录</h1>
		<form id="loginForm" class="zcform" method="post">
			<p>
				<label for="commonUserAccount"> <input type="text"
					id="commonUserAccount" name="commonUserAccount" placeholder="用户名" autocomplete="off"
					autofocus>
				</label>
			</p>
			<p>
				<label for="commonUserPassword"> <input type="password"
					id="commonUserPassword" name="commonUserPassword" placeholder="密码"
					autocomplete="off">
				</label>
			</p>
			<p>
				<label for="kaptchaKey"> <input class="required"
					type="text" id="kaptchaKey" name="kaptchaKey" placeholder="验证码">
				</label>
			</p>
			<p>
				<label> <img alt="验证码" style="cursor:pointer" title="点击换一张"
					id="kaptchaImg" src="<@spring.url ''/>/user/Kaptcha.jpg"
					onclick="this.src=this.src+'?time='+Math.random()">
				</label>
			</p>
			<p>
				<label>
				<input class="submit" type="submit" id="loginBtn" value="登录"/>
				</label>
			</p>
			<p>
				<a href="<@spring.url ''/>/">返回首页</a>
				-
				<a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/register">没有账户？立即注册 →</a>
			</p>
		</form>
		<p class="alert alert-danger" id="warningbox"></p>
	</div>
</body>
</html>
