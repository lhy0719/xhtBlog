/*$(function(){
	$("#userInfo").on("click",function() {
		$("#userInfo_box").toggle();
		$("#userInfo_box").mouseleave(function() {
			$(this).css("display", "none");
		})
	});
});*/
function userlogout(){
	$.ajax({
		url : webAppBasePath
				+ "/user/commonUser/commonUserLogout",
		type : "post",
		dataType : "json",
		success : function(result) {
			// 返回提示信息
				location.href=webAppBasePath+"/";
			
		}
	});
}
function gotoArticle(){
	window.location.href=webAppBasePath+"/user/commonUser/goToPortalPage?templateName=blog/article"
}