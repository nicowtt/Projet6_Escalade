<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!-- Navigation
================================================== -->
<nav class="navbar navbar-inverse " role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand page-top" href="/home">Les amis de l'escalade</a>
    </div>
    <!-- All elements on navbar -->
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/searchSite">Recherche</a></li>
            <li><a href="/createClimbingSite">Enregistrer un nouveau site d'escalade</a></li>
            <li><a href="/exchangeTopoPapier">Echange de Topo papier</a></li>
        </ul>

        <!-- identification -->
        <ul class="nav navbar-nav navbar-right" > <li class="dropdown">

            <!-- if user is login -->
            <c:if test="${!empty log}"><p style="color:white;"><c:out value="${log} "/>
                <br><a href="/personalSpace">Mon espace personnel</a><br>
                <a href="/dologout">Se deconnecter </a></p>
            </c:if>

            <!-- if user not login -->
            <c:if test="${empty log}">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">S'identifier <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li class="dropdown-header">Vous n'êtes pas identifié.</li>
                <li><form class="navbar-form" method="post" action="/dologin" modelAttribute="utilisateur">
                    <input type="text" class="form-control" name="email" placeholder="email" /><br />
                    <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe"/>
                    <input type="submit" class="btn btn-primary btn-xs btn-block" /></form></li>
                <li class="disabled"><a href="#">Mot de passe oublié ?</a></li>
                <li class="enable"><a href="/newUserGet">Créer un compte...</a></li>
            </ul>
        </li>
            </c:if>
        </ul>

    </div>
</nav>
