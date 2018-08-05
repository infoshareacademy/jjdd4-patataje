<div class="container text-center my-auto">
    <br>
    <div class="table-responsive-md">
        <table class="table table-sm table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">nazwa stacji</th>
                <th scope="col">wyświetlenia</th>
            </tr>
            </thead>
    <#if WCList?has_content>
<tbody>
<tr>
        <#list WCList>
            <#items as containerName>
                <th scope="row">${(containerName?index)+1}</th>
                <td>${containerName.stationName} </td>
                <td>${containerName.views}</td>
                    </tr>
            </#items>
</tbody>
        </#list>
    <#else>
<p class="alert-warning">Brak wyświetleń stacji w bazie.</p>
    </#if>
        </table>
    </div>
</div>
    <div class="overlay"></div>