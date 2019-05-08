<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <%--<link rel="stylesheet" type="text/css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css" href="<%=request.getContextPath()%>resources/css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css/style.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css">--%>

    <%--<link rel="stylesheet" media="screen" type="text/css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css" title="main_css"--%>
          <%--href="resources/css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css/style.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css.css" />--%>

</head>
<c:redirect url="/home"/>




<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- Javascript de Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>



</html>




<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Index Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<a href="/index">Go To Home Page</a>--%>
<%--</body>--%>
<%--</html>--%>