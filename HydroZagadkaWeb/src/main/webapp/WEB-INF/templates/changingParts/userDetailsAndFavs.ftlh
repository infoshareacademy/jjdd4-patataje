<style>
    table,tr,th{
        border:solid 2px black;
        position:center;
    }
</style>
<section class="content-section bg-light" id="userInfo">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="uploadfile">
                <h3> Witaj <h3 class="text-secondary mb-0">  ${nameSurname}</h3></h3>
                <#if favs??>
                <h4>Twoje ulubione zbiorniki to:</h4>
                    <#list favs>
                    <table>
                        <#items as favContainers>
                            <tr>
                                <th>${favContainers.containerName}</th>
                                <th>${favContainers.stationName}</th>
                                <th>${favContainers.province}</th>
                            </tr>
                        </#items>
                    </table>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</section>