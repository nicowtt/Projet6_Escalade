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

    <!-- display for change availability of one topoPapier -->
    <br>
    <div id="changeAvailability">
        <c:forEach items="${topoPapier}" var="to">
        <f:form modelAttribute="updateTopoPapier" method="post" action="/changeAvailabilityTopoPapier/${to.id}">
            <p> mettre la disponiblité sur:</p>
            <f:select path="disponibilite" id="availability">
                <f:option value="true">Oui</f:option>
                <f:option value="false">Non</f:option>
            </f:select>

            <%--<!-- for bean validation -->--%>
            <input type="hidden" value="nomTopo" name="nomTopo">
            <input type="hidden" value="description" name="description">
            <input type="hidden" value="nomCreateur" name="nomCreateur">

            <input type="submit" value="Changer la disponibilité">

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


