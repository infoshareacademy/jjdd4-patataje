<div class="container text-center my-auto">
    <br><br>
    <h4>stacje pomiarowe:</h4>
<div class="table-responsive-md">
<table class="table table-sm table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nazwa stacji</th>
        <th scope="col">Liczba wyświetleń</th>
    </tr>
    </thead>
<tbody>
<tr>
<#if WCList??>
    <#list WCList>
        <#items as containerName>
                <th scope="row">${(containerName?index)+1}</th>
                <td>${containerName.stationName} </td>
                <td>${containerName.views}</td>
                    </tr>
        </#items>
</tbody>
            </table>
            </div>
              </div>
    </#list></#if>
    <div class="overlay"></div>