
<section id="users">
    <div class="container text-center my-auto">
        <br><br>
        <h3>Statystyki zbiorników:</h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nazwa zbiornika</th>
            <th scope="col">Województwo</th>

        </tr>
        </thead>
    <tbody>
    <tr>
        <#if WCList??>
            <#list WCList>
                <#items as  waterContainer>
                <th scope="row">${(user?index)+1}</th>
                <td>${WaterContainer.stationName} </td>
                <td>${WaterContainer.province}</td>
               </tr>
                </#items>
</tbody>
</table>
            </#list></#if>
        <div class="overlay"></div>