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

<body>

<div class="container">
    <section class="row">

    <!-- Body
    ================================================== -->

    <!-- display site -->
    <p></p>
    <p><c:forEach items="${site}" var="si">
        <div class="col-xs-6 col-sm-4 col-md-3">
            <a href="<%=pathWebcontent%>/climbingSite/${si.id}" class="thumbnail">
            <img src="${si.urlPhotoSite}" alt="${si.urlPhotoSite}" style="width:250px;height:250px; border:0px;" class="img-rounded">
            </a>
                <span class="label label-default"><c:out value="${si.localisationPays}"/></span>
                <span class="label label-info"><c:out value="Nom: ${si.nomSite}"/></span>
                <p></p>
                <span class="label label-info"><c:out value="Nombre de secteur: ${si.nombreDeSecteur}"/></span>
                <p></p>

        </div>
    </c:forEach>

    </section>

</div>

<%--<%@include file="_include/footer.jsp"%>--%>

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


