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
        <h3>Enregistrement d'un nouveau Secteur d'escalade:</h3>

        <p></p>
        <f:form modelAttribute="secteur" method="post" action="/createNewSectorPost/${secteur.site_id}">
            <table class="lignesEspacees">
                <tr>
                    <!-- display for "nom secteur" -->
                    <td> Nom du nouveau secteur:* </td>
                    <td><f:input path="nomSecteur" type="text" id="nomSite" size="20" placeholder="obligatoire"  cssStyle=""/></td>
                    <td><f:errors path="nomSecteur" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "descriptionSecteur" -->
                    <td>Description:</td>
                    <td><f:input path="descriptionSecteur" type="text" id="descriptionSecteur" size="40" placeholder=""  cssStyle=""/></td>
                    <td><f:errors path="descriptionSecteur" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "acces" -->
                    <td>Accés:</td>
                    <td><f:input path="acces" type="text" id="acces" placeholder="" size="20"  cssStyle="" /></td>
                    <td><f:errors path="acces" cssClass="errors" /></td>
                </tr>
                <tr>
                    <!-- display for "altitude base" -->
                    <td>Altitude: </td>
                    <td><f:input path="altitudeBase" type="text" id="altitudeBase" size="20" placeholder="" cssStyle=""/> mètres</td>
                    <td><f:errors path="altitudeBase" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "orientation" -->
                    <td>Orientation: </td>
                    <td><f:input path="orientation" type="text" id="orientation" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="orientation" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "Type de roche" -->
                    <td>Type de roche: </td>
                    <td><f:input path="typeRoche" type="text" id="city" size="20" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="typeRoche" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "Nombre de voies" -->
                    <td>Nombre de voies:* </td>
                    <td><f:input path="nombreDeVoies" type="number" id="nombredevoie" size="20" placeholder="obligatoire" cssStyle=""/></td>
                    <td><f:errors path="nombreDeVoies" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "cotation" -->
                    <td>Cotation:* </td>
                    <td><f:input path="cotation" type="text" id="cotation" placeholder="obligatoire" cssStyle=""/></td>
                    <td><f:errors path="cotation" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "url photo secteur" -->
                    <td>Url photo du secteur (voies visible): </td>
                    <td><f:input path="urlPhotoSecteur" type="url" id="orientation" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="urlPhotoSecteur" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "coordonne gps" -->
                    <td>Coordonné gps: </td>
                    <td><f:input path="coordonneGps" type="text" id="coordonnegps" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="coordonneGps" cssClass="errors"/></td>
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


