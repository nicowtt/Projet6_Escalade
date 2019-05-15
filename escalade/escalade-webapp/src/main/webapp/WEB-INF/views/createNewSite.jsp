<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Les amis de l'escalade</title>
    <meta name="description" content="Le site de partage pour fan d'escalade">

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

    <spring:url value="/resources/css/style.css " var="stylecss" />
    <link href="${stylecss}" rel="stylesheet" />

</head>

<header class="raw">

    <!-- Navigation
    ================================================== -->
    <nav class="navbar navbar-inverse" role="navigation">
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
                <li><a href="/echangeTopo">Echange de Topo</a></li>
            </ul>

            <!-- identification -->
            <ul class="nav navbar-nav navbar-right"> <li class="dropdown">

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

</header>
<div class="container">

    <!-- Body
    ================================================== -->

    <!-- display for write new climbing site -->
    <br>
    <div id="NewSite">
        <h3>Enregistrement d'un nouveau Site d'escalade:</h3>

        <p></p>
        <f:form modelAttribute="site" method="post" action="/createClimbingSite">
            <table class="lignesEspacees">
                <tr>
                    <!-- display for "nomSite" -->
                    <td> Nom du nouveau site:* </td>
                    <td><f:input path="nomSite" type="text" id="nomSite" size="20" placeholder="obligatoire"  cssStyle=""/></td>
                    <td><f:errors path="nomSite" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "descriptionSite" -->
                    <td>Description:</td>
                    <td><f:input path="descriptionSite" type="text" id="descriptionSite" size="40" placeholder=""  cssStyle=""/></td>

                    <td><f:errors path="descriptionSite" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "localisationDepartement" -->
                    <td>Departement:*</td>
                    <td><f:input path="localisationDepartement" type="text" id="localisationDepartement" placeholder="obligatoire" size="20"  cssStyle="" /></td>
                    <td><f:errors path="localisationDepartement" cssClass="errors" /></td>
                </tr>
                <tr>
                    <!-- display for "localisationPays" -->
                    <td>Pays:* </td>
                    <td><f:input path="localisationPays" type="text" id="localisationPays" size="20" placeholder="obligatoire" cssStyle=""/></td>
                    <td><f:errors path="localisationPays" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "urlPhotoSite" -->
                    <td>Url Photo du site: </td>
                    <td><f:input path="urlPhotoSite" type="url" id="urlPhotoSite" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="urlPhotoSite" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "nombre de secteur" -->
                    <td>Nombre de secteur: </td>
                    <td><f:input path="nombreDeSecteur" type="number" id="city" size="15" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="nombreDeSecteur" cssClass="errors"/></td>
                </tr>
            </table>
            <p></p>
            <p>(*) obligatoire</p>
            <p></p>
            <input type="submit" value="Envoyer">
        </f:form>
    </div>



</div>


<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- Javascript de Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<%--<!-- Optional JavaScript -->--%>
<%--<!-- jQuery first, then Popper.js, then Bootstrap JS -->--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>--%>




</body>
</html>


