<nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a class="js-scroll-trigger" href="#page-top">Góra</a>
        </li>
        <#if isLoggedIn = "admin">
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="/admin">Panel admina</a>
        </li>
        </#if>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#about">O Aplikacji</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#check">Znajdź zbiornik</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#upload">Prześlij swoje dane</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#contact">O Autorach</a>
        </li>
        <li class="sidebar-nav-item">
        <#if isLoggedIn == "none">
            <div><#include "logInUser.ftlh"></div>
        </#if>
        </li>
        <li>
            <#if isLoggedIn=="user" || isLoggedIn=="admin">
                <div>
            <#include "logOutUser.ftlh">
                </div>
            </#if>
        </li>
    </ul>
</nav>