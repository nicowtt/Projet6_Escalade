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

    <!-- display site -->
    <br>
    <p>Topo papier possédé(s):</p>

    <div id="listerTopoPapier">
        <table class="table">
            <tr>
                <th>Nom du topo</th>
                <th>Site couvert</th>
                <th>Description (Autres sites couverts):</th>
                <th>Nom createur</th>
                <th>Date de creation</th>
                <th>Date de maj</th>
                <th>Disponibilité</th>

            </tr>
            <c:forEach items="${topoPapier}" var="to">
                <tr>
                    <td>${to.nomTopo}</td>
                    <td>${to.site.nomSite}</td>
                    <td>${to.description}</td>
                    <td>${to.nomCreateur}</td>
                    <td>${to.dateCreation}</td>
                    <td>${to.dateMaj}</td>
                    <td>${to.disponibilite ? "oui" : "non"} </td>
                    <td><a href="<%=pathWebcontent%>/availabilityTopoPapier/${to.id}">Changer la diponibilité </a></td>
                    <td><a href="<%=pathWebcontent%>/updatePaperTopo/${to.id}">modifier </a></td>
                    <td><a href="<%=pathWebcontent%>/comfirmationForDeleteTopoPaper/${to.id}">supprimer </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="TopoPapierDemandeReserveration">
        <p>Demande de réservation en cours:</p>
        <table class="table">
            <tr>
                <th>N°de reservation</th>
                <th>Nom du topo</th>
                <th>Email du preteur</th>
            </tr>
        <c:forEach items = "${reservationEnvoie}" var="re">
            <tr>
                <td>${re.id}</td>
                <td>${re.topopapier.nomTopo}</td>
                <td>${re.emailPretOk}</td>
            </tr>
        </c:forEach>
        </table>
    </div>


    <div id="TopoPapierReceptionReserveration">
        <p>réception de réservation en cours:</p>
        <table class="table">
            <tr>
                <th>N° de reservation</th>
                <th>Nom du topo</th>
            </tr>
            <c:forEach items = "${reservationReception}" var="re">
            <tr>
                <td>${re.id}</td>
                <td>${re.topopapier.nomTopo}</td>
                <td><a href="<%=pathWebcontent%>/acceptBooking/${re.id}/${re.topoPapier_id}">Accepter la demande</a> </td>
            </tr>
            </c:forEach>
        </table>
    </div>

    <div id="TopoPapierReserveration ok">
        <p>Demande de réservation validé:</p>
        <table class="table">
            <tr>
                <th>N°de reservation</th>
                <th>Nom du topo</th>
                <th>Email du preteur</th>
            </tr>
            <c:forEach items = "${reservationOK}" var="re">
                <tr>
                    <td>${re.id}</td>
                    <td>${re.topopapier.nomTopo}</td>
                    <td>${re.emailPretOk}</td>
                </tr>
            </c:forEach>
        </table>
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

</html>



