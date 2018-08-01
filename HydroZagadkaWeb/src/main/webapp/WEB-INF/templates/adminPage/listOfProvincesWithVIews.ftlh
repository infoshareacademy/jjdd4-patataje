<div class="container text-center my-auto">
    <br><br>
    <h4>województwa:</h4>
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
<#if provincestats??>
    <#list provincestats>
        <#items as province>
                <th scope="row">${(province?index)+1}</th>
                <td>${province.province} </td>
                <td>${province.views}</td>
                    </tr>
        </#items>
</tbody>
            </table>
            </div>
                       </div>
    </#list></#if>
    <div class="overlay"></div>