<#if provincestats??>
<div class="container text-center my-auto">
    <br><br>
    <div class="table-responsive-md">
<table class="table table-sm table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Województwo</th>
        <th scope="col">liczba wyświetleń</th>
    </tr>
    </thead>
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
</table>
            </div>
                       </div>
<#else>
<p class="alert-warning">Brak wyświetleń województw.</p>
</#if>
    <div class="overlay"></div>