<section class="content-section bg-light" id="userInfo">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="userInfo">
                <h3> Witaj <h3 class="text-secondary mb-0">  ${nameSurname}</h3></h3>
                <div class="col-sm"></div>
              <#if favs??>
                  <h4>Twoje ulubione zbiorniki to:</h4>
                <div class="table-responsive-md col-6">
                    <table class="table table-sm table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">województwo</th>
                            <th scope="col">nazwa zbiornika</th>
                            <th scope="col">nazwa stacji</th>
                        </tr>
                        </thead>
              <tbody>
              <tr>
                    <#list favs>
                        <#items as favContainers>
                             <th scope="row">${(favContainers?index)+1}</th>
                                <td>${favContainers.province}</td>
                                <td>${favContainers.containerName}</td>
                                <td>${favContainers.stationName}</td>
                            </tr>
                        </#items>
                    </tbody>
                    </#list>
              </#if>
            </table>
            </div>
                <div class="col-sm"></div>
            </div>
        </div>
</section>