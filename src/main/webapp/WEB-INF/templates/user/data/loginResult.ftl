<#import "/spring.ftl" as spring />
<#if commonloginResult??>
	<#switch commonloginResult>
		<#case "loginSuccess">
		{"success":true,"url":"<@spring.url ''/>/public/index"}
		<#break>
		<#case "pswIsWrong">
		{"success":false,"msg":"<@spring.message 'loginResult.pwdwrong.msg'/>"}
		<#break>
		<#case "userIsNotExist">
		{"success":false,"msg":"<@spring.message 'loginResult.nouser.msg'/>"}
		<#break>
		<#default>
	</#switch>
</#if>