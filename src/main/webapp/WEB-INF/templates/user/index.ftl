<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html ng-app="cmsIndexPage" ng-controller="cmsIndexController">
<head>
<title>二蛋是条狗</title> 
<#include "/html_meta.ftl" parse=true encoding="UTF-8"/>
<#include "/portal_headCSS.ftl" parse=true encoding="UTF-8"/>
<#include "/portal_headJS.ftl" parse=true encoding="UTF-8"/>
<script type="text/javascript" src="<@spring.url ''/>/scripts/user/index.js"></script>
</head>
<body>
	<#include "/portal_commonHeader.ftl" parse=true encoding="UTF-8"/>
	<div class="container-fluid">
	<div class="row">
	<div class="col-md-1">
	</div>
	<div class="col-md-10">
	<div class="row">
		<div class="col-md-12">
			<div class="flexslider">
			  <ul class="slides">
			    <li ng-repeat="i in img">
			    	<h3>好多狗啊</h3>
			      	<img height="500px" src="<@spring.url ''/>/js/jquery/flexSlider/images/{{i}}.jpg" />
			    </li>
			  </ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
		<h2>技术分享</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
		<p><a class="btn btn-success btn-large" href="#">更多</a></p>
		</div>
		<div class="col-md-4">
		<h2>生活点滴</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
		<p><a class="btn btn-success btn-large" href="#">更多</a></p>
		</div>
		<div class="col-md-4">
		<h2>大神日记</h2>
		<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
		<p><a class="btn btn-success btn-large" href="#">更多</a></p>
		</div>
	</div>
	<hr>
	<footer class="tc">
	<p class="logo-desc">he is a good man。</p>
	</footer>
	</div>
	<div class="col-md-1">
	</div>
	</div>
	</div>
	<!-- /container -->
	<a id="up" href="javascript:" class="upToTop">回到顶部</a>
</body>
</html>
