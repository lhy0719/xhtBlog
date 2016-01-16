<#import "/spring.ftl" as spring />
<#if registerResult??>
	<#switch registerResult>
		<#case "registerSuccess">
		{"success":false,"msg":"<@spring.message 'registerResultult.registerSuccess.msg'/>"}
		<#break>
		<#case "acountRepetition">
		{"success":false,"msg":"<@spring.message 'registerResultult.acountRepetition.msg'/>"}
		<#break>
		<#default>
	</#switch>
</#if>