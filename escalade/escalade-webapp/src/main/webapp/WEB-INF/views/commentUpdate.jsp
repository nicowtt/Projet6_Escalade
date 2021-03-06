<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>

<head>

    <%@include file="_include/head.jsp"%>

</head>

<body data-spy="scroll" data-target=".navbar">

<body>
<header class="raw">

    <%@include file="_include/header.jsp"%>

</header>
</body>
</body>


<div class="container">

    <!-- Body
    ================================================== -->

    <!-- display for update comment -->
    <br>
    <div id="listerCommentaire">
        <table class="table">
            <tr>
                <th>Date Commentaire</th>
                <th>Nom</th>
                <th>commentaire</th>
            </tr>
            <c:forEach items="${comment}" var="co">
                <tr>
                    <td>${co.dateCommentaire}</td>
                    <td>${co.utilisateur.prenom}</td>
                    <td>${co.commentaire}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div id="EcrireCommentaire">
        <c:forEach items="${comment}" var="co">
        <f:form modelAttribute="commentaire" method="post" action="${pageContext.request.contextPath}/updateComment/${co.id}">
            <p>
                <label for="EcrireCommentaire">Voulez-vous modifier le commentaire ?</label><br/>
                    <f:input size="60" path="commentaire" cssStyle=""/>
                    <f:errors path="commentaire" cssClass="errors"/>
            <p></p>
            <input type="submit" value="Envoyer Commentaire">

        </f:form>
        </c:forEach>
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


