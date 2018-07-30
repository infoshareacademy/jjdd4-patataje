<div class="container text-center my-auto">
<div class="row">
    <div class="col-sm"></div>
<div class="col-9">
    <br><br>
    <h3>Najczęściej wyświetlane stacje pomiarowe:</h3>
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
<#list WCList>
    <#items as waterContainer>
                <th scope="row">${(waterContainer?index)+1}</th>

                <td>${waterContainer.containerName} </td>
                <td>${statistics.views}</td>
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