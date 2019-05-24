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

    <spring:url value="/resources/css/style.css" var="stylecss" />
    <link href="${stylecss}" rel="stylesheet" />

  </head>

  <header class="raw">

      <%@include file="_include/header.jsp"%>

  </header>
  <div class="container">

    <!-- Body
    ================================================== -->

    <!-- Climbing site title -->
    <c:forEach items="${site}" var="si">
      <p class="flotte">
      <c:if test="${si.officelSite}"><img src="https://image.noelshack.com/fichiers/2019/19/4/1557415844-officiel-logo-les-amis-de-lescalade-vide.png" alt="logo escalade officiel" style="width:191px;height:92px;border:0;"></c:if>
      </p>
      <%--<b>TAG: Site Officiel Les amis de l'esclade !</b>--%>
      <p></p>
    <h1><c:out value="${si.nomSite}" /></h1>
      <p></p>

      <p><b>Pays: </b><c:out value="${si.localisationPays}" /></p>
      <p><b>Departement: </b><c:out value="${si.localisationDepartement}" /></p>
      <p><b>Nombre de secteur: </b><c:out value="${si.nombreDeSecteur}" /></p>

    <!-- Site Description-->
      <c:out value="${si.descriptionSite}" /><br>

      <!-- Site action: display or write comment-->
      <p></p>
      <a href="/commentRead/${si.element_id}">Voir les commentaires sur ce site</a><br>
      <a href="/commentWrite/${si.element_id}">Ecrire un commentaire sur ce site</a><br>

      <!-- Site action: add site to personnal space-->
      <p></p>
      <a href="/createTopoPaper/${si.id}">Déclarer un topo papier possédé pour ce site</a>

      <!-- Site action: tag site "officiel les amis de l'escalade ** see only by member associative-->
      <p></p>
      <!-- if no tag-->
      <c:if test="${!si.officelSite}">
      <c:if test="${user.membreAssociation}"><a href="/addTagForOfficialSite/${si.id}">Tagger ce site "Officiel Les amis de l'escalade !"</a></c:if>
      </c:if>
      <!-- if tag is present-->
      <c:if test="${si.officelSite}">
      <c:if test="${user.membreAssociation}"><a href="/deleteTagForOfficialSite/${si.id}">Enlever le tag "Officiel Les amis de l'escalade !"</a></c:if>
      </c:if>
      <p></p>

      <!-- Site action: delete site ** see only by member associative-->
      <c:if test="${user.membreAssociation}"><a href="/comfirmDeleteSite/${si.id}">supprimer le site</a></c:if>

      <p></p>

      <!-- Picture-->
      <img src="${si.urlPhotoSite}" alt="${si.urlPhotoSite}" style="width:500px;height:500px;border:0;">
      <!-- lien pour crée un nouveau secteur-->
    <p></p>
      <p><a href="/createNewSector/${si.id}">Ajouter un nouveau secteur sur ce site </a></p>
    </c:forEach>


    <!-- Sectors table
    ================================================== -->

    <h3>Les Secteurs:</h3>
      <div id="listerSecteur">
      <table class="table">
      <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Accés</th>
        <th>Altitude base</th>
        <th>orientation</th>
        <th>Type de roche</th>
        <th>Nombre de voies</th>
        <th>cotation</th>
        <th>Coordonnés GPS</th>
        <th>Modification</th>
        <th colspan="2">Commentaires</th>
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
        <td><a href="/createNewWay/${s.id}">Ajouter une nouvelle voie</a></td>
        <td><a href="/commentRead/${s.element_id}">Voir</a></td>
        <td><a href="/commentWrite/${s.element_id}">Ecrire</a></td>
      </tr>
        </c:forEach>
      </table>
      </div>

      <h3>Les voies:</h3>

      <!-- Way pictures (depend of sectors number) -->
        <c:forEach items="${secteur}" var="s">
      <img src="${s.urlPhotoSecteur}" alt="${s.urlPhotoSecteur}" style="width:500px;height:500px;border:0;">
        </c:forEach>


    <!-- Way table
    ================================================== -->

      <div id="listerVoie">
      <table class="table">
      <tr>
        <th>Nom du secteur</th>
        <th>Numero de voie</th>
        <th>Nom de la voie</th>
        <th>Temp d'escalade</th>
        <th>description</th>
        <th>longueur</th>
        <th>cotation</th>
        <th>hauteur</th>
        <th>Precision equipement</th>
        <th>Nom de l'ouvreur</th>
        <th>Date ouverture</th>
        <th>status</th>
        <th colspan="2">commentaire</th>
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
          <td><a href="/commentRead/${v.element_id}">Voir</a></td>
          <td><a href="/commentWrite/${v.element_id}">Ecrire</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </div>

  <%--<!-- Optional JavaScript -->--%>
  <%--<!-- jQuery first, then Popper.js, then Bootstrap JS -->--%>
  <%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
  <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>--%>
  <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>--%>

  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <!-- Javascript de Bootstrap -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

  </body>
</html>
