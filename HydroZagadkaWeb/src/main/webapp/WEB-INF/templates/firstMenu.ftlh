

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.5/css/select2.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.5/js/select2.full.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js" defer></script>
    <style>
        .watercontainer,.province,.station {
            width: 100%;
        }
    </style>
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
        // $('.province').onchange(function () {
        //  $('.watercontainer').css("display","inline")
        // });
        $('.province').select2();
        $('.watercontainer').select2();
        $('.station').select2();
    });
</script>

