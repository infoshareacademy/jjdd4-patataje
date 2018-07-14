<form action="/addSomeData" method="post" class="form-inline">
    <input type="checkbox" name="province">
    <input type="checkbox" name="waterContainer">
    <div class="form-group">
        <label class="col-md-1 control-label" for="lunch">Lunch:</label>
    </div>
    <div class="form-group">
        <select id="lunch" class="selectpicker" data-live-search="true" title="Please select a lunch ...">
            <option>Hot Dog, Fries and a Soda</option>
            <option>Burger, Shake and a Smile</option>
            <option>Sugar, Spice and all things nice</option>
            <option>Baby Back Ribs</option>
            <option>A really really long option made to illustrate an issue with the live search in an inline form</option>
        </select>
    </div>
    <button type="submit">Wyslij</button>
</form>
