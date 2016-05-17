$(function() {
	$("#loginForm").validate(
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
							commonUserAccount : {
								required : true,
								userAccount : true
								}
						},
						focusInvalid: true,
						focusCleanup: true,
						messages : {
							kaptchaKey : {
								remote : "验证码错误"
							}
						},
						submitHandler : function(form) {
							$.ajax({
								url : webAppBasePath
										+ "/user/commonUser/generateKeyPair",
								type : "post",
								dataType : "json",
								success : function(result) {
									var key =bodyRSA(result.rsaempoent,result.rsamodule);
									var thisPwd = $("#commonUserPassword").val();
									var encryptedPsw = encryptedString(key, encodeURIComponent(thisPwd));
									$("#commonUserPassword").val(encryptedPsw);
									$.ajax({
												url : webAppBasePath
														+ "/user/commonUser/commonUserLogin",
												type : "post",
												dataType : "json",
												data : $(form).serialize(),
												success : function(result) {
													$("#commonUserPassword").val(thisPwd);
													$("#kaptchaKey").val(null);
													$('#kaptchaImg').hide().attr('src', webAppBasePath+'/user/Kaptcha.jpg?time='+Math.random()).fadeIn();
													// 返回提示信息
													var msg=result.msg;
													if(msg=='userIsNotExist'){
														callShake();
														$("#warningbox").html("该用户不存在！").show(500).fadeOut(5000);
													}else if(msg=='pswIsWrong'){
														callShake();
														$("#warningbox").html("密码错误！").show(500).fadeOut(5000);
													}else if(msg=='userIsLocked'){
														callShake();
														$("#warningbox").html("账户被锁定，请联系工作人员！").show(500).fadeOut(5000);
													}else{
														$("#warningbox").html("登录成功 :)").removeClass('alert-danger').addClass('alert-info').show(1000,function(){location.href=webAppBasePath+"/";}).fadeOut(2000);
													}
													
												},
												error:function(){
													callShake();
													$("#commonUserPassword").val(null);
													$("#kaptchaKey").val(null);
													$("#warningbox").html("请求错误！请重新登录。").show(500).fadeOut(5000);
												}
											});
								}
							});
						}
		});
});
function bodyRSA(rsaempoent,rsamodule){
	setMaxDigits(130);
  	return new RSAKeyPair(rsaempoent,"",rsamodule); 
}
function shake(id, a, d) {
	c = a.shift();
	s(id, c);
	if (a.length > 0) {
		setTimeout(function() {
			shake(id, a, d);
		}, d);
	} else {
		try {
			g(id).position = 'static';
			wp_attempt_focus();
		} catch (e) {}
	}
}
function s(id, pos) {
	g(id).left = pos + 'px';
}

function g(id) {
	return document.getElementById(id).style;
}
function callShake(){
	var p = [15, 30, 15, 0, -15, -30, -15, 0];
	p = p.concat(p.concat(p));
	var i = document.forms[0].id;
	g(i).position = 'relative';
	shake(i, p, 20);
}