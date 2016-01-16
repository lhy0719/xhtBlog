var cmsIndexPage=angular.module("cmsIndexPage", ["ngSanitize"]);
cmsIndexPage.controller("cmsIndexController",function($scope,$http){
	$scope.img=[1,2,3,4,5,6,7];
	$http({
		method:"POST",
		url:webAppBasePath+"/user/blog/index",
		params:{
			templateName:"user/homeTopics"
		}
	}).success(function(data, status, headers, config) {
		$scope.index=data;
	}).error(function(data, status, headers, config) {
		$scope.index={};
	});
});
function globalSearch(){
	$("#searchConditionForm").submit();
}
$(function(){
	$('.flexslider').flexslider({
		animation: "slide",
		directionNav: false,
        slideshowSpeed: 5000, // 自动播放速度毫秒
        animationSpeed: 1000 //滚动效果播放时长
    });
	//绑定页面滚动事件
    $(window).bind('scroll',function(){
        var len=$(this).scrollTop();
        if(len>=100){
            //显示回到顶部按钮
            $('#up').show();
        }else{
            //影藏回到顶部按钮
            $('#up').hide();
        }
    });
    //绑定回到顶部按钮的点击事件
    $('#up').click(function(){
        //动画效果，平滑滚动回到顶部
        $("html,body").animate({ scrollTop: 0 });
        //不需要动画则直接
        //$(document).scrollTop(0)
    })
});