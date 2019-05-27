<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>

<head>

    <%@include file="_include/head.jsp"%>

</head>

<header class="raw">

    <%@include file="_include/header.jsp"%>

</header>
<div class="container">

    <!-- Body
    ================================================== -->

    <!-- display for write new climbing site -->
    <br>
    <div id="NewSite">
        <h3>Enregistrement d'un nouveau Secteur d'escalade:</h3>

        <p></p>
        <f:form modelAttribute="secteur" method="post" action="${pageContext.request.contextPath}/createNewSectorPost/${secteur.site_id}">
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


