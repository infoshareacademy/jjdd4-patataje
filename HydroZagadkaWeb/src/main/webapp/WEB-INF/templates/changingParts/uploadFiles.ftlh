
<section class="content-section bg-light" id="upload">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h2> Aby zobaczyć wykresy wyślij plik zip:</h2>

                <form class="formclass" method="post" action="/loadservlet">
                    <input type="file" name="file" accept=".zip">
                    <input type="submit">
                </form>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>