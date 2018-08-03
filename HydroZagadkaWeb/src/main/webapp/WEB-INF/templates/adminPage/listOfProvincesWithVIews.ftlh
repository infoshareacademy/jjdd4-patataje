
<div class="container text-center my-auto">
    <br>
    <div class="table-responsive-md">
<table class="table table-sm table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">województwo</th>
        <th scope="col">wyświetlenia</th>
    </tr>
    </thead>
<#if provincestats?has_content>
<tbody>
<tr>
    <#list provincestats>
        <#items as province>
                <th scope="row">${(province?index)+1}</th>
                <td>${province.province} </td>
                <td>${province.views}</td>
                    </tr>
        </#items>
</tbody>

    </#list>
<#else>
<p class="alert-warning">Brak wyświetleń województw w bazie.</p>
</#if>
</table>
    </div>
</div>

    <div class="overlay"></div>