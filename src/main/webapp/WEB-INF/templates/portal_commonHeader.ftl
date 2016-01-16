<#import "/spring.ftl" as spring />
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
      	</button>
		<a href="<@spring.url ''/>/" class="navbar-brand" style="padding:10px">
			<div class="text-logo">
				<span>
					二蛋君<br>
					<span class="logo-desc">er dan shi tiao gou.</span>
				</span>
			</div>
		</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	<ul class="nav navbar-nav">
		<li <#if templateName??><#else>class="active"</#if>><a href="<@spring.url ''/>">首页</a></li>
		<li <#if templateName=='technology'>class="active"</#if>><a href="<@spring.url ''/>/user/goto/technology">技术分享</a></li>
		<li <#if templateName=='life'>class="active"</#if>><a href="<@spring.url ''/>/user/goto/life">生活点滴</a></li>
		<li <#if templateName=='worship'>class="active"</#if>><a href="<@spring.url ''/>/user/goto/worship">大神日记</a></li>
		<li <#if templateName=='about'>class="active"</#if>><a href="<@spring.url ''/>/user/goto/about">关于</a></li>
	</ul>
	<form class="navbar-form navbar-left" role="search">
	  <div class="form-group">
	  	<div class="input-group">
		    <input type="text" class="form-control" placeholder="搜索文章或大神...">
		    <span class="input-group-btn">
	        	<button class="btn btn-default" type="button"><i class="glyphicon glyphicon-search"></i></button>
	      	</span>
      	</div>
	  </div>
	</form>
	<ul class="nav navbar-nav navbar-right">
		<#if commonUser??>
			<li><button class="btn userInfobtn btn-success l" onclick="gotoArticle()"><i class="fa fa-pencil"></i> 写文章</button>
				<a id="userInfo" class="navbar-brand tc" style="width:160px" data-toggle="dropdown">
				<img class="userInfoImg l" src="<@spring.url ''/>/js/portals/images/avatar-max-img.png" width="24" height="24">
				${commonUser.commonUserAccount}
				<b class="caret"></b>
				</a>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dLabel">
					<li><a href="<@spring.url ''/>/user/commonUser/goToPortalPage?templateName=blog/article"><i class="fa fa-pencil"></i>
							写文章</a></li>
					<li><a href="<@spring.url ''/>/user/commonUser/goToPortalPage?templateName=blog/articleList"><i
							class="fa fa-user"></i> 我的主页</a></li>
					<li><a href="/favourites"> <i class="fa fa-heart"></i>
							我喜欢的
					</a></li>
					<li><a href="/bookmarks"> <i class="fa fa-bookmark"></i>
							我的收藏
					</a></li>
					<li><a href="/settings"><i class="fa fa-cogs"></i> 设置</a></li>
					<li class="dropdown"><a tabindex="-1" href="#"><i
							class="fa fa-question-circle"></i> 帮助</a>
						<ul class="dropdown-menu">
							<li><a href="http://www.jianshu.com/notebooks/547/latest"
								target="_blank">帮助中心</a></li>
							<li><a target="_blank" href="/contact">联系我们</a></li>
						</ul></li>
					<li><a href="#" onclick="userlogout()">
							<i class="fa fa-sign-out"></i> 退出
					</a></li>
				</ul>
			</li>
			<!-- 用户信息 -->
			<div class="userinfo_box abs dn" id="userInfo_box">
				<div title="${commonUser.commonUserName}"><i class="userIcon"></i><#if commonUser.commonUserName??>${commonUser.commonUserName}<#else><a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/modifyUserInfo">没有录入</a></#if></div>
				<div title="${commonUser.commonUserPhone}"><i class="telIcon"></i><#if commonUser.commonUserPhone??>${commonUser.commonUserPhone}<#else><a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/modifyUserInfo">没有录入</a></#if></div>
				<div title="${commonUser.commonUserMail}"><i class="emailIcon"></i><#if commonUser.commonUserMail??>${commonUser.commonUserMail}<#else><a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/modifyUserInfo">没有录入</a></#if></div>
				<hr/>
				<footer class="tc">
				<a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/modifyUserInfo">修改信息</a><a href='#'>|</a><a href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/modifyUserPsw">修改密码</a>
				</footer>
				<span class="userinfo_box-arrow"> <em></em> </span>
			</div><!-- 用户信息 -->
		<#else>
			<li><a id="login_a" class="navbar-brand" href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/login">登录</a><a class="navbar-brand" href="<@spring.url ''/>/user/commonUser/goToExcludePortalPage?templateName=user/register">注册</a></li>
		</#if>
	</div>
	</div>
	</div>
</div>
<div style="height:53px;background-color:#e7e7e7"></div>