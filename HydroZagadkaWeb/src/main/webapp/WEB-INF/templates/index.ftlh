<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap-select test page</title>

    <meta charset="utf-8">
    <meta lang="pl">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js" defer></script>
    <script src=https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.js defer></script>
    <link rel="stylesheet" href="../../css/firstForm.css">
    <script src="../../js/firstForm.js"></script>
</head>

<body>
<form action="/addSomeData" method="post" class="form-inline">
<div class="form-group">
    <select id="province" class="selectpicker" data-live-search="true" title="Województwo">
        <option value="0">Hot Dog, Fries and a Soda</option>
        <option value="1">Burger, Shake and a Smile</option>
        <option value="2">Sugar, Spice and all things nice</option>
        <option value="3">Baby Back Ribs</option>
        <option value="4">A really really long option made to illustrate an issue with the live search in an inline form</option>
    </select>
</div>
<div class="form-group">
    <select id="watercontainer" class="selectpicker" data-live-search="true" title="Zbiornik">
        <option value="0">Hot Dog, Fries and a Soda</option>
        <option value="1">Burger, Shake and a Smile</option>
        <option value="2">Sugar, Spice and all things nice</option>
        <option value="3">Baby Back Ribs</option>
        <option value="4">A really really long option made to illustrate an issue with the live search in an inline form</option>
    </select>
</div>
<div class="form-group" id="station" style="display: none">
    <select class="selectpicker" data-live-search="true" title="Stacja" >
        <option>Hot Dog, Fries and a Soda</option>
        <option>Burger, Shake and a Smile</option>
        <option>Sugar, Spice and all things nice</option>
        <option>Baby Back Ribs</option>
        <option>A really really long option made to illustrate an issue with the live search in an inline form</option>
    </select>
</div>
<button type="submit" id="wyslij">Wyslij</button>
</form>
</body>
</html>