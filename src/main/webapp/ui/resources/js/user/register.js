$(function() {
	$("#registerForm")
			.validate(
					{
						rules : {
							kaptchaKey : {
								required : true,
								remote : {
									url : webAppBasePath
											+ "/user/commonUser/validateKaptchaKey", //后台处理程序    
									type : "post", //数据发送方式   
									dataType : "json", //接受数据格式       
									data : { //要传递的数据   
										kaptchaKey : function() {
											return $("#kaptchaKey").val();
										}

									}

								}
							},
							commonUserPassword:{
								required : true,
								userPassword:true
							},
							recommonUserPassword : {
								required : true,
								equalTo : "#commonUserPassword"
							},
							commonUserAccount : {
								required : true,
								rangelength: [5,16],
								userAccount : true,
								remote : {
									url : webAppBasePath
											+ "/user/commonUser/validateUserAccount", //后台处理程序    
									type : "post", //数据发送方式   
									dataType : "json", //接受数据格式       
									data : { //要传递的数据   
										commonUserAccount : function() {
											return $("#commonUserAccount").val();
										}

									}

								}
							}
						},
						messages : {
							kaptchaKey : {
								remote : "验证码错误"
							},
							recommonUserPassword : {
								equalTo : "两次输入的密码不一致"
							},
							commonUserAccount : {
								remote : "该用户名已存在"
							}
						},
						focusInvalid: true,
						focusCleanup: true,
						submitHandler : function(form) {
							$.ajax({
										url : webAppBasePath
												+ "/user/commonUser/registerCommonUser",
										type : "post",
										dataType : "json",
										data : $(form).serialize(),
										success : function(result) {
											$('#kaptchaImg').hide().attr('src', webAppBasePath+'/user/Kaptcha.jpg?time='+Math.random()).fadeIn();  
											// 返回提示信息
											var msg=result.msg;
											if(msg=='registerSuccess'){
												$("#warningbox").html("注册成功 :)").removeClass('alert-danger').addClass('alert-info').show(1000,function(){location.href=webAppBasePath+"/";}).fadeOut(2000);
											}else if(msg=='acountRepetition'){
												$("#warningbox").html("该用户名已被注册！").show(500).fadeOut(5000);
											}else{
												$("#warningbox").html("请求错误！注册失败。").show(500).fadeOut(5000);
											}
											
										},
										error:function(XMLHttpRequest, textStatus, errorThrown) {
												$("#kaptchaKey").val(null);
												$("#warningbox").html("请求错误！注册失败。").show(500).fadeOut(5000);
										}
									});
						},
						errorPlacement : function(error, element) {
							error.appendTo(element.parent());
						}
					});
});