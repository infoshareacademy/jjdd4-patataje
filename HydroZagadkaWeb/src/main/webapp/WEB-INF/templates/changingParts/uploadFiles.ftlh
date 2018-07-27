<section class="content-section bg-light" id="upload">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto" id="uploadfile">
                <h2> Aby zobaczyć wykresy wyślij plik zip:</h2>
                <br>
            sdasdasd
            <#if noZip??>
                <#--<div style="--warning: ">-->

                    tutaj błąd
                    #{noZip}
            <#--</div>-->
            </#if>
                <br>


                <form class="formclass" method="post" id="loadfile" action="/loadservlet" enctype="multipart/form-data">
                    <div>
                        <input type="file" name="file" accept=".zip" ="btn btn-primary" required = 'required'></div>
                    <br>
                    <br>
                    <div>
                        <input type="submit" class="btn btn-primary"></div>
                </form>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>