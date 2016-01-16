var blogListPage=angular.module("blogListPage", ["ngSanitize"]);
blogListPage.controller("blogListController",function($scope,$http){
	var url=webAppBasePath+"/user/blog/index";
	$http({
		method:"POST",
		url:url,
		params:{
			templateName:"user/homeTopics"
		}
	}).success(function(data, status, headers, config) {
		$scope.index=data;
	}).error(function(data, status, headers, config) {
		$scope.index={};
	});
});