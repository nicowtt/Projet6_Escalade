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

    <spring:url value="/resources/css/style.css" var="stylecss" />
    <link href="${stylecss}" rel="stylesheet" />

    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>resources/css/style.css">--%>

      <%--<link rel="stylesheet" media="screen" type="text/css" title="main_css"--%>
      <%--href="/resources/css/style.css" />--%>

  </head>

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
    <!-- Tous les éléments qui peuvent aller dans la barre du haut -->
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/recherche">Recherche</a></li>
        <li><a href="/creationTopo">Creation de Topo</a></li>
        <li><a href="/echangeTopo">Echange de Topo</a></li>
      </ul>

      <!-- identification -->
      <ul class="nav navbar-nav navbar-right"> <li class="dropdown">

        <!-- if user is login -->
        <c:if test="${!empty log}"><p style="color:white;"><c:out value="${log} "/>
          <br><a href="/dologout">Se deconnecter </a></p>
        </c:if>

        <!-- if user not login -->
        <c:if test="${empty log}">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">S'identifier <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li class="dropdown-header">Vous n'êtes pas identifié.</li>
          <li><form class="navbar-form" method="post" action="/dologin" modelAttribute="utilisateur">
            <input type="text" class="form-control" name="email" placeholder="email" /><br />
            <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe"/>
            <input type="submit" class="btn btn-primary btn-xs btn-block" /></form></li>
          <li class="disabled"><a href="#">Mot de passe oublié ?</a></li>
          <li class="disabled"><a href="#">Créer un compte...</a></li>
        </ul>
      </li>
        </c:if>
      </ul>

    </div>
    </nav>

  </header>
  <div class="container">

    <!-- Body
    ================================================== -->

    <!-- Climbing site title -->
    <h1><c:forEach items="${site}" var="si">
      <c:out value="${si.nomSite}" />
    </c:forEach></h1>

    <!-- Site Description-->
    <p><c:forEach items="${site}" var="si">
      <c:out value="${si.descriptionSite}" />
    </c:forEach></p>

    <!-- Picture-->
    <c:forEach items="${site}" var="si"><img src="${si.urlPhoto}" alt="${si.urlPhoto}" style="width:500px;height:500px;border:0;"></c:forEach>

    <!-- Sectors table
    ================================================== -->
    <h3>Les Secteurs:</h3>

  <div id="listerSecteur">
    <table class="table">
      <tr>
        <th>Nom</th><th>Description</th><th>Accés</th><th>Altitude base</th><th>orientation</th><th>Type de roche</th>
        <th>Nombre de voies</th><th>cotation</th><th>Coordonnés GPS</th><th>Commentaires</th><th>Commentaires</th>
        <%--<th>Element_id</th><th>Secteur_id</th>--%>
      </tr>
      <c:forEach items="${secteur}" var="s">
        <tr>
          <td>${s.nomSecteur}</td>
          <td>${s.descriptionSecteur}</td>
          <td>${s.acces}</td>
          <td>${s.altitudeBase}m</td>
          <td>${s.orientation}</td>
          <td>${s.typeRoche}</td>
          <td>${s.nombreDeVoies}</td>
          <td>${s.cotation}</td>
          <td>${s.coordonneGps}</td>
          <td><a href="/comment/${s.element_id}">Voir</a></td>
          <td><a href="editProduit?ref=${s}">Ecrire</a></td>
          <%--<td><a href="editProduit?ref=${p.reference}">Editer</a></td>--%>
            <%--<td><a href="deleteProduit?ref=${p.reference}">Supprimer</a></td>--%>
        </tr>
      </c:forEach>
    </table>
  </div>

    <h3>Les voies:</h3>

    <!-- Way pictures (depend of sectors number) -->
    <c:forEach items="${secteur}" var="s"><img src="${s.urlPhoto}" alt="${s.urlPhoto}" style="width:500px;height:500px;border:0;"></c:forEach>

    <!-- Way table
    ================================================== -->
  <div id="listerVoie">
    <table class="table">
      <tr>
        <th>Nom du secteur</th><th>Numero de voie</th><th>Nom</th><th>Temp d'escalade</th><th>description</th><th>longueur</th><th>cotation</th>
        <th>hauteur</th><th>Precision equipement</th><th>Ouverture</th><th>Date ouverture</th><th>status</th>
      </tr>

      <c:forEach items="${voie}" var="v">
        <tr>
          <td>${v.secteur.nomSecteur}</td>
          <td>${v.numero}</td>
          <td>${v.nomVoie}</td>
          <td>${v.tempDescalade}h</td>
          <td>${v.descriptionVoie}</td>
          <td>${v.longueur}</td>
          <td>${v.cotation}</td>
          <td>${v.hauteur}m</td>
          <td>${v.precisionEquipement}</td>
          <td>${v.ouvertureEtEquipement}</td>
          <td>${v.dateOuverture}</td>
          <td>${v.statut}</td>
            <%--<td>${p.element_id}</td>--%>
            <%--<td><a href="editProduit?ref=${p.reference}">Editer</a></td>--%>
            <%--<td><a href="deleteProduit?ref=${p.reference}">Supprimer</a></td>--%>
        </tr>
      </c:forEach>
    </table>
  </div>
  </div>

  <%--<!-- jQuery -->--%>
  <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
  <%--<!-- Javascript de Bootstrap -->--%>
  <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>--%>

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



  </body>
</html>
