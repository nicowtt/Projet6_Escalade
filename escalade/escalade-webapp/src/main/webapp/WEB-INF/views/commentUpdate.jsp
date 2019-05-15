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

<body data-spy="scroll" data-target=".navbar">

<body>
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
                <li><a href="/createClimbingSite">Creation de Topo</a></li>
                <li><a href="/echangeTopo">Echange de Topo</a></li>
            </ul>

            <!-- identification -->
            <ul class="nav navbar-nav navbar-right"> <li class="dropdown">

                <!-- user must be login -->
                <c:if test="${!empty log}"><p style="color:white;"><c:out value="${log} "/>
                <br><a href="/dologout">Se deconnecter </a></p>
                </c:if>
            </ul>

        </div>
    </nav>
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
        <f:form modelAttribute="updatedComment" method="post" action="/updateComment/${co.id}">
            <p>
                <label for="EcrireCommentaire">Voulez-vous modifier le commentaire ?</label><br/>
                    <f:input size="60" path="commentaire" cssStyle=""/>
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


