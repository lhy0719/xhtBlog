<#import "/spring.ftl" as spring />
<#switch templateKey>
<#case "updateCommonUser">
{"success":true,"msg":"用户信息已修改"}
<#break>
<#case "resetPsw">
{"success":true,"msg":"用户密码已修改,请重新登录！"}
<#break>
<#case "commonUserLogout">
{"success":true,"msg":"用户已退出登录"}
<#break>
<#case "registerCommonUser">
{"success":true,"msg":"${registerResult!!}"}
<#break>
<#case "commonUserLogin">
{"msg":"${loginResult!!}"}
<#break>
<#case "getCityInfoByUserOID">
{"success":true,"msg":"您没有登录"}
<#break>
<#case "validateKaptchaKey">
${kaptchaResult!!}
<#break>
<#case "validateUserAccount">
${validateResult!!}
<#break>
<#case "generateKeyPair">
{"rsamodule":"${rsamodule}","rsaempoent":"${rsaempoent}"}
<#break>
</#switch>