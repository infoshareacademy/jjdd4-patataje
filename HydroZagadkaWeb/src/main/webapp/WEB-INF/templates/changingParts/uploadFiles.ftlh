<section class="content-section bg-light" id="upload">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="uploadfile">
                <h2> Prześlij plik zip ze swoimi danymi:</h2>
                <br>
                <form class="formclass" method="post" id="loadfile" action="/loadservlet" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-sm"></div>
                        <div class="col-6">
                            <div class="form-group">
                                <input type="file" name="file"
                                       class="filestyle"
                                       data-text="Wybierz plik"
                                       accept=".zip"
                                       required oninvalid="this.setCustomValidity(' Nie wybrałeś pliku .zip')"
                                       oninput="setCustomValidity('')">
                            </div>
                        </div>
                        <div class="col-sm"></div>
                    </div>
                    <br>
                    <div>
                        <input type="submit" class="btn btn-primary"></div>
                </form>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>