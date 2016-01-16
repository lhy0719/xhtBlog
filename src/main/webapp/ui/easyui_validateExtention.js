$.extend($.fn.validatebox.defaults.rules, {    
	variable:{    
        validator: function(value, param){
        	var reg=/^[a-zA-Z][a-zA-Z0-9_]*$/;
            return reg.test(value);
        },    
        message: '请输入字母、数字和下划线.'   
    },
    picture:{    
        validator: function(value, param){
        	var support=["png","jpg","jpeg","gif"];
        	var split=value.lastIndexOf(".");
        	var extName=value.substr(split+1,value.length);
            return jQuery.inArray(extName.toLowerCase(),support)!=-1;
        },    
        message: '请选择图片文件.'   
    },
    wordFile:{    
        validator: function(value, param){
        	var support=["doc","docx"];
        	var split=value.lastIndexOf(".");
        	var extName=value.substr(split+1,value.length);
            return jQuery.inArray(extName.toLowerCase(),support)!=-1;
        },    
        message: '请选择WORD文件.'   
    },
    minLength: {    
        validator: function(value, param){    
            return value.length >= param[0];    
        },    
        message: 'Please enter at least {0} characters.'   
    },
    maxLength: {    
        validator: function(value, param){    
            return value.length <= param[0];    
        },    
        message: 'Please enter at most {0} characters.'   
    },
    phone : {// 验证电话号码
		validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	    },
        message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '手机号码格式不正确(正确格式如：18640583821)'
    },
    phoneOrMobile:{//验证手机或电话
    	validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message:'请填入手机或电话号码,如13688888888或020-8888888'
	},
	 email:{
        validator : function(value){
 	        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
 	    },
 	    message : '请输入有效的电子邮件账号(例：abc@126.com)'
 	}
});  