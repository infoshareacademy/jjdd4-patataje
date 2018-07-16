<!DOCTYPE html>
<html>
<head>
    <title>Users List</title>
    <link type="text/css" href="/css/firstForm.css"/>
</head>
<body>
<h2>AAAAAAAAAAAA</h2>
<form action="/addSomeData" method="get" class="form-inline">

    <div class="form-group">
        <select class="province" id="province" name="state" title="Województwo" onchange="this.form.submit()">
            <#list provinces as item>
                <option value=${item}>${item}</option>
            </#list>
        </select>
</form>
<form action="/aaaa" method="get" class="form-inline">
    <select class="watercontainer" id="watercontainer" title="Zbiornik" >
   <#list watercontainer as item>
       <option value=${item}>${item}</option>
   </#list>
    </select>
</form>

<form action="/bbbb" method="get" class="form-inline">
    <select class="station" id="station" title="Stacja" >

    </select>
</form>
</div>
<script>
    $(document).ready(function() {
        $('.province').select2();
    });
</script>
</body>
</html>
