var blogPage=angular.module("blogPage", ["ngSanitize"]);
//blogPage.config(function($httpProvider) {
//	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
//	$httpProvider.defaults.transformRequest = function(data) {
//		if (data === undefined) {
//			return data;
//		}
//		return $.param(data);
//	}
//});
blogPage.directive('ckeditor', function() {
    return {
        require : '?ngModel',
        link : function(scope, element, attrs, ngModel) {
            var ckeditor = CKEDITOR.replace(element[0], {
            	customConfig : webAppBasePath+"/editor/ckeditor/blog_config.js",
        		height:370
            });
            if (!ngModel) {
                return;
            }
            ckeditor.on('instanceReady', function() {
                ckeditor.setData(ngModel.$viewValue);
            });
            ckeditor.on('pasteState', function() {
                scope.$apply(function() {
                    ngModel.$setViewValue(ckeditor.getData());
                });
            });
            ngModel.$render = function(value) {
                ckeditor.setData(ngModel.$viewValue);
            };
        }
    };
});
blogPage.controller("blogController",function($scope,$http){
	$http({
		method:"POST",
		url:webAppBasePath+"/user/blog/findChildTopic"
	}).success(function(data, status, headers, config) {
		$scope.topics=data;
	}).error(function(data, status, headers, config) {
		$scope.topics={};
	});
	var vm = $scope.vm = {
            show_error: false,
            show_type: 2,
            article: {}
        };

    vm.submit = function () {
//    	document.getElementById("article_form").action=webAppBasePath + "/user/blog/addOrUpdateCmsArticle";
//    	document.getElementById("article_form").submit();
//    	$("#article_form").ajaxSubmit({
//    		url: webAppBasePath + "/user/blog/addOrUpdateCmsArticle",
//    		type : "post",
//			dataType : "json",
//    		success: function(data) {
//    			vm.reset();
//    		}
//    	});
    	var form =document.getElementById("article_form");
    	$http({
    		method:"POST",
    		url: webAppBasePath + "/user/blog/addOrUpdateCmsArticle",
    		data:vm.article,
    		headers: {'Content-Type': undefined},
    		transformRequest: function(data) {
		       var formData = new FormData();
		       formData.append("topicName", data.topicName);
		       formData.append("title", data.title);
		       formData.append("content", data.content);
		       return formData;
    		}
    	}).success(function(data, status, headers, config) {
    		vm.reset();
    		callMessageBox("文章已保存");
    	}).error(function(data, status, headers, config) {
    	});
    };
    vm.reset = function (article_form) {
    	// 重置表单
        vm.article = {};
//        article_form.$setPristine();
    }
});