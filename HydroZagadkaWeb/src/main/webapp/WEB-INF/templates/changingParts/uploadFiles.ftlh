<section class="content-section bg-light" id="upload">
    <style>
.loadbtn{

}
    </style>
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="uploadfile">
                <h2> Aby zobaczyć wykresy wyślij plik zip:</h2>
<br>
                <form class="formclass" method="post" id="loadfile" action="/loadservlet" enctype="multipart/form-data"><div>
                    <a class="btn btn-primary btn-xl js-scroll-trigger" onclick="getElementById('filebtn').click()">Wczytaj Plik</a>
                    <input type="file" id="filebtn" name="file" accept=".zip" class="btn btn-primary" style="display: none;"></input>
                </div>
                    <br>
                    <br><div>
                        <button type="submit" class="btn btn-primary">Wyślij</button>
                </form>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>