<section class="content-section bg-light" id="userInfo">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="uploadfile">
                <h3> Witaj <h3 class="text-secondary mb-0">  ${nameSurname}</h3></h3>
                <#if usersFavs?has_content>
                <h4>Twoje ulubione zbiorniki to:</h4>
                    <#list usersFavs>
                        <#items as favContainers>
                            ${favContainers.containerName}
                        </#items>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</section>