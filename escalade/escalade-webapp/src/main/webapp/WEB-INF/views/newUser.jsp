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

    <%@include file="_include/header.jsp"%>

</header>
<div class="container">

    <!-- Body
    ================================================== -->

    <!-- display for write new user -->
    <br>
    <div id="NewUser">
        <h3>Inscription nouvel utilisateur:</h3>
        <p></p>
        <f:form modelAttribute="utilisateur" method="post" action="/newUser">
            <table class="lignesEspacees">
                <tr>
                    <!-- display for "raison sociale" -->
                    <td>Raison sociale: </td>
                    <td><f:select path="raisonSociale" id="social">
                        <f:option value="" />
                        <f:option value="Mr" />
                        <f:option value="Mme" />
                    </f:select></td>
                </tr>
                <tr>
                    <!-- display for "nom" -->
                    <td> Votre Nom:* </td>
                    <td><f:input path="nom" type="text" id="name" size="20" placeholder=""  cssStyle=""/></td>
                    <td><f:errors path="nom" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "prenom" -->
                    <td>Votre prénom:*</td>
                    <td><f:input path="prenom" type="text" id="firstName" size="20" placeholder=""  cssStyle=""/></td>
                    <td><f:errors path="prenom" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "mot de passe" -->
                    <td>Choisissez un mot de passe:*</td>
                    <td><f:input path="motDePasse" type="password" id="password" size="20"  cssStyle="" /></td>
                    <td><f:errors path="motDePasse" cssClass="errors" /></td>
                </tr>
                <tr>
                    <!-- display for "adresse" -->
                    <td>Votre adresse: </td>
                    <td><f:input path="adresse" type="text" id="adress" size="40" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="adresse" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "code postal" -->
                    <td>Votre code postal: </td>
                    <td><f:input path="codePostal" type="number" id="postalCode" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="codePostal" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "ville" -->
                    <td>Votre ville: </td>
                    <td><f:input path="ville" type="text" id="city" size="15" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="ville" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "Pays" -->
                    <td>Votre pays: </td>
                    <td><f:input path="pays" type="text" id="country " size="15" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="pays" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "numero de telephone" -->
                    <td>Votre numero de télephone: </td>
                    <td><f:input path="numeroTelephone" type="tel" id="telephoneNumber" size="20" cssStyle=""/></td>
                    <td><f:errors path="numeroTelephone" cssClass="errors"/></td>
                </tr>
                <tr>
                    <!-- display for "email" -->
                    <td>Votre E-mail:* </td>
                    <td><f:input path="email" type="email" id="email" size="30" placeholder="" cssStyle=""/></td>
                    <td><f:errors path="email" cssClass="errors"/>
                </tr>
                <tr>
                    <!-- display for "membre association" -->
                    <td>Membre association: </td>
                    <td><f:select path="membreAssociation" id="association">
                        <f:option value="true">Oui</f:option>
                        <f:option value="false">Non</f:option>
                    </f:select></td>
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


