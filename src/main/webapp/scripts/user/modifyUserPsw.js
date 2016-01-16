$(function(){
	$("#modifyUserPswForm").validate({
				rules:{
					commonUserPassword:{
						required : true,
						userPassword:true
					},
					recommonUserPassword : {
						required : true,
						equalTo : "#commonUserPassword"
					}
					},
				messages : {
					recommonUserPassword : {
						equalTo : "两次输入的密码不一致"
					}
				},
				focusInvalid: true,
				focusCleanup: true,
				submitHandler : function(form) {
					$.ajax({
								url : webAppBasePath
										+ "/user/commonUser/resetPsw",
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
//										$.Zebra_Dialog('会话过期，请重新登录', {
//										    'type':'information',
//										    'title':'消息',
//										    'buttons':[],
//										    'width':350,
//										    'auto_close':2000,
//											'position': ['center',  'top + 60'],
//										    'onClose':  function(caption) {
//										    	location.reload();
//										    }
//										});
									}else{
										callMessageBox(result.msg);
										setTimeout(function(){
											location.href=webAppBasePath+"/user/login";
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