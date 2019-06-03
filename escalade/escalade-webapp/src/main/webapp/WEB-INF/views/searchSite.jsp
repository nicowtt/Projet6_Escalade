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

    <!-- display for search climbing Site -->
    <br>

    <div id="searchClimbingSite2">
        <f:form modelAttribute="search" method="post" action="${pageContext.request.contextPath}/home">
            <p></p>
            <%--<!-- search by country -->--%>
            <h3>Recherche Site d'escalade:</h3>
            <p></p>
            </br>
            <%--<p>Par Pays:</p>--%>
            <p>Pays:</p>
            <f:select path="localisationPays">
                <f:option value=""/>
                <c:forEach items="${pays}" var="pa">
                    <f:option value="${pa}">${pa}</f:option>
                </c:forEach>
            </f:select>
            <p></p>

            <%--<!-- search by departement -->--%>
            <p>Département:</p>
            <f:select path="localisationDepartement">
                <f:option value=""/>
                <c:forEach items="${departements}" var="de">
                    <f:option value="${de}">${de}</f:option>
                </c:forEach>
            </f:select>
            <p></p>

            <%--<!-- search by sector number -->--%>
            <p>Nombre de secteur:</p>
            <f:select path="nombreDeSecteur">
                <f:option value=""/>
                <c:forEach items="${sectorNumber}" var="se">
                    <f:option value="${se}">${se}</f:option>
                </c:forEach>
            </f:select>
            <p></p>

            <%--<!-- search by site name -->--%>
            <p>Nom du site:</p>
            <f:select path="nomSite">
                <f:option value=""/>
                <c:forEach items="${site}" var="si">
                    <f:option value="${si}">${si}</f:option>
                </c:forEach>
            </f:select>
            <p></p>

            <input type="submit" value="Rechercher">

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


