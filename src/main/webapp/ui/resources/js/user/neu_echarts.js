$("div[neu-chart]").each(function(i){
	var obj=echarts.init(this);
	var id=$(this).attr('id');
	$(this).data(id,obj);
});
var echarts=angular.module("echarts", ["ngSanitize"]);
echarts.config(function($httpProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$httpProvider.defaults.transformRequest = function(data) {
		if (data === undefined) {
			return data;
		}
		return $.param(data);
	}
});
echarts.controller("chartController", function($scope,$http,$element,$attrs) {
	var chartObj=$element.data($attrs.id);
	var chartData=angular.fromJson($attrs.chartData);
	var chartURL=$attrs.chartUrl;
	var chartOptions=angular.fromJson($attrs.chartOptions);
	var chartType=$attrs.neuChart;
	chartData.chartType=chartType;
	$http({
		method:"POST",
		url:chartURL,
		data:chartData
	}).success(function(data, status, headers, config) {
		var series=chartOptions.series;
		for (var i=0;i<data.length;i++){
			series[i].data=data[i];
		}
		chartObj.setOption(chartOptions);
	}).error(function(data, status, headers, config) {
		$scope.option={};
		chartObj.setOption($scope.option);
	});
	
});
echarts.controller("reportController", function($scope,$http,$element,$attrs) {
	var reportData=angular.fromJson($attrs.reportData);
	var reportUrl=$attrs.reportUrl;
	$http({
		method:"POST",
		url:reportUrl,
		data:reportData
	}).success(function(data, status, headers, config) {
		$scope.data=data;
	}).error(function(data, status, headers, config) {
		$scope.data={};
	});
	
});