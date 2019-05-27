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
        <h3>Enregistrement d'un nouveau Site d'escalade:</h3>

        <p></p>
        <f:form modelAttribute="site" method="post" action="${pageContext.request.contextPath}/createClimbingSite">
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
                    <td>Nombre de secteur:* </td>
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


