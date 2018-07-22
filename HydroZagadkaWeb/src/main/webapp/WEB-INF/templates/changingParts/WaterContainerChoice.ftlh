<style>


</style>
<br>
<form>
    <div class="form-group" id="provincelist">

        <label for="province">Województwo:</label><br>

        <select class="form-control col-sm-5" id="province" name="state" title="Województwo" required>
            <option selected>[Wybierz]</option>
            <option value="pomorskie">pomorskie</option>
            <option value="lubuskie">lubuskie</option>
            <option value="lubelskie">lubelskie</option>
            <option value="podkarpackie">podkarpackie</option>
            <option value="warmińsko-mazurskie">warmińsko-mazurskie</option>
            <option value="zachodniopomorskie">zachodniopomorskie</option>
            <option value="podlaskie">podlaskie</option>
            <option value="opolskie">opolskie</option>
            <option value="śląskie">śląskie</option>
            <option value="mazowieckie">mazowieckie</option>
            <option value="podlaskie">podlaskie</option>
            <option value="dolnośląskie">dolnośląskie</option>
            <option value="wielkopolskie">wielkopolskie</option>
            <option value="świętokrzyskie">świętokrzyskie</option>
            <option value="małopolskie">małopolskie</option>
            <option value="łódzkie">łódzkie</option>
        </select>



        <div class="form-group" id="watercontainerlist" >
            <br>
            <label for="watercontainer">Zbionik:</label><br>

            <select class="form-control col-sm-5 is-invalid" id="watercontainer" name="watercontainer" title="Zbiornik" required>
                <option value="-1">[Wybierz]</option>
            </select> <div class="invalid-feedback">
            Proszę wybrać zbiornik.
        </div></div>
    </div>
</form>

<div>
    <form class="history-form" name="subform">
        <div class="form-group" id="stationlist">
            <label for="station">Stacja pomiarowa:</label><br>
            <select class="form-control col-sm-5 is-invalid" id="station" name="station" title="Stacja" required>
                <option value="-1">[Wybierz]</option>
            </select><div class="invalid-feedback">
            Proszę wybrać stację.
        </div><br>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="checkdate">
                <label class="form-check-label" for="checkdate">Sprawdź dla wybranego przedziału dat</label>
            </div>
            <div id="dates" style="display: none">
                <input type="date" id="startdate">
                <input type="date" id="enddate">
            </div>
            <br>
            <a class="btn btn-primary btn-xl js-scroll-trigger" id="movetocharts" href="#charts" style="display: none">Sprawdź</a>
            <button type="submit" class="btn btn-primary" onclick="getElementById('movetocharts').click()">Sprawdź</button>
        </div>

    </form>

</div>