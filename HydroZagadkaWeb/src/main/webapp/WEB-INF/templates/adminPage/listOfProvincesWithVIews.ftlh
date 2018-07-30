<div class="container text-center my-auto">
<div class="row">
    <div class="col-sm"></div>
<div class="col-9">
    <br><br>
    <h3>Wyświetlenia województw:</h3>
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
            </table>
            </div>
            </div>
                <div class="col-sm">

                </div>
            </div>
</#list>
    <div class="overlay"></div>