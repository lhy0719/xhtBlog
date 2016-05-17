$(function(){
	$("#modifyUserInfoForm").validate({
				rules:{
					commonUserPhone:{
						required : true,
						phoneOrMobile:true
					},
					commonUserName:{
						required : true,
						chineseName:true
					}
					},
				focusInvalid: true,
				focusCleanup: true,
				submitHandler : function(form) {
					$.ajax({
								url : webAppBasePath
										+ "/user/commonUser/updateCommonUser",
								type : "post",
								dataType : "json",
								data : $(form).serialize(),
								success : function(result,textStatus,jqXHR) {
									var a=jqXHR.getResponseHeader("sessionstatus");
									if(a=='timeout'){
										callMessageBox('会话过期，请重新登录');
										setTimeout(function(){
											location.reload();
										}, 1000);
									}else{
										callMessageBox(result.msg);
										setTimeout(function(){
											location.href=webAppBasePath+"/";
										}, 1000);
									}
								}
						});
					},
				errorPlacement: function(error, element) {  
					    error.appendTo(element.parent());  
					}
			});
});