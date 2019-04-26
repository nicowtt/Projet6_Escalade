<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Grimpez</title>
    <meta name="description" content="Le site de partage pour fan d'escalade">

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

    <spring:url value="/resources/css/style.css " var="styleCss" />
    <link href="${styleCss}" rel="stylesheet" />

  </head>

  <header class="raw">

    <!-- Navigation
    ================================================== -->
    <nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand page-top" href="/index">Accueil Grimpez.com</a>
    </div>
    <!-- Tous les éléments qui peuvent aller dans la barre du haut -->
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/recherche">Recherche</a></li>
        <li><a href="/echangeTopo">Echange Topo</a></li>
      </ul>

      <!-- identification -->
      <ul class="nav navbar-nav navbar-right"> <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">S'identifier <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li class="dropdown-header">Vous n'êtes pas identifié.</li>
          <li><form class="navbar-form" method="post" action="/login?redirect=/">
            <input type="text" class="form-control" name="username" placeholder="Identifiant" /><br />
            <input type="password" class="form-control" name="password" placeholder="Mot de passe"/>
            <input type="submit" class="btn btn-primary btn-xs btn-block" /></form></li>
          <li class="disabled"><a href="#">Mot de passe oublié ?</a></li>
          <li class="disabled"><a href="#">Créer un compte...</a></li>
        </ul>
      </li>
      </ul>

    </div>
    </div>
    </nav>

  </header>
  <div class="container">

  <h1>Site d'En beys</h1>

    <h3>Les Secteurs:</h3>

  <div id="listerSecteur">
    <table class="table">
      <tr>
        <th>Numero de secteur</th><th>Nom</th><th>Description</th><th>Accés</th><th>Altitude base</th><th>orientation</th><th>Type de roche</th>
        <th>Nombre de voies</th><th>cotation</th><th>Coordonnés GPS</th>
        <%--<th>Element_id</th><th>Secteur_id</th>--%>
      </tr>
      <c:forEach items="${secteur}" var="s">
        <tr>
          <td>${s.id}</td>
          <td>${s.nom}</td>
          <td>${s.description}</td>
          <td>${s.acces}h</td>
          <td>${s.altitudeBase}m</td>
          <td>${s.orientation}</td>
          <td>${s.typeRoche}</td>
          <td>${s.nombreDeVoies}</td>
          <td>${s.cotation}</td>
          <td>${s.coordonneGps}</td>

            <%--<td>${p.element_id}</td>--%>

            <%--<td><a href="editProduit?ref=${p.reference}">Editer</a></td>--%>
            <%--<td><a href="deleteProduit?ref=${p.reference}">Supprimer</a></td>--%>
        </tr>
      </c:forEach>

    </table>
  </div>

    <h3>Les voies:</h3>

  <div id="listerVoie">
    <table class="table">
      <tr>
        <th>Numero de secteur</th><th>Numero de voie</th><th>Nom</th><th>Temp d'escalade</th><th>description</th><th>longueur</th><th>cotation</th>
        <th>hauteur</th><th>Precision equipement</th><th>Ouverture</th><th>Date ouverture</th><th>status</th>
        <%--<th>Element_id</th><th>Secteur_id</th>--%>
      </tr>


      <c:forEach items="${voie}" var="p">
        <tr>
          <td>${p.secteur_id}</td>
          <td>${p.numero}</td>
          <td>${p.nom}</td>
          <td>${p.tempDescalade}h</td>
          <td>${p.description}</td>
          <td>${p.longueur}</td>
          <td>${p.cotation}</td>
          <td>${p.hauteur}m</td>
          <td>${p.precisionEquipement}</td>
          <td>${p.ouvertureEtEquipement}</td>
          <td>${p.dateOuverture}</td>
          <td>${p.statut}</td>
            <%--<td>${p.element_id}</td>--%>

            <%--<td><a href="editProduit?ref=${p.reference}">Editer</a></td>--%>
            <%--<td><a href="deleteProduit?ref=${p.reference}">Supprimer</a></td>--%>
        </tr>
      </c:forEach>

    </table>


  </div>
  </div>

  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <!-- Javascript de Bootstrap -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


  </body>
</html>
