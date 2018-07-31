<div>
<#if user.adminaaa == true>
<#include "adminMainPage.ftlh">
    <#else>
<#include "../index.ftlh"> <script> alert('Nie masz uprawnień by wejść na tę stronę!')</script>

</#if></div>