<#switch templateKey>
<#case "deleteCommonUserByOID">
delete CommonUser as commonUser where commonUser.commonUserOID=?
<#break>
</#switch>