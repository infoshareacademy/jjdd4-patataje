<#if user??>
<div class="container text-center my-auto">
<div class="row">
    <div class="col-sm"></div>
<div class="col-9">
    <br><br>


<div class="table-responsive-md">
<table class="table table-sm table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">email</th>
        <th scope="col">imię</th>
        <th scope="col">uprawnienia admina</th>
    </tr>
    </thead>
<tbody>
<tr>

    <#list Uzytkownik>
        <#items as  user>
                <th scope="row">${(user?index)+1}</th>
                <td>${user.email} </td>
                <td>${user.name}</td>
                    <td><#if user.adminaaa == true> <strong>TAK</strong>
                    <#else>NIE
                    </#if></td></tr>
        </#items>
</tbody>
            </table>
            </div>
            </div>
                <div class="col-sm">

                </div>
            </div></div>
    </#list><#else>
<p class="alert-warning">Brak użytkowników w bazie.</p>
</#if>
    <div class="overlay"></div>