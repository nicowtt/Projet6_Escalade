<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>

<c:set var = "createur" scope = "session" value = "${element.utilisateur_id}"/>

  <head>

    <%@include file="_include/head.jsp"%>

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
      <p><h1><c:out value="${si.nomSite}" /></h1></p>

      <p><b>Pays: </b><c:out value="${si.localisationPays}" /></p>
      <p><b>Département: </b><c:out value="${si.localisationDepartement}" /></p>
      <p><b>Nombre de secteur: </b><c:out value="${si.nombreDeSecteur}" /></p>

    <!-- Site Description-->
      <p><c:out value="${si.descriptionSite}" /><br></p>

      <!-- Site action: display or write comment-->
      <p>
      <a href="<%=pathWebcontent%>/commentRead/${si.element_id}">Voir les commentaires sur ce site</a><br>
        <c:if test="${user != null}"><a href="<%=pathWebcontent%>/commentWrite/${si.element_id}">Ecrire un commentaire sur ce site</a></c:if><br></p>

      <!-- Site action: add site to personnal space-->
      <p><c:if test="${user != null}"><a href="<%=pathWebcontent%>/createTopoPaper/${si.id}">Déclarer un topo papier possédé pour ce site</a></c:if></p>

      <!-- Site action: tag site "officiel les amis de l'escalade ** see only by member associative-->
      <p>
      <!-- if no tag-->
      <c:if test="${!si.officelSite}">
      <c:if test="${user.membreAssociation}"><a href="<%=pathWebcontent%>/addTagForOfficialSite/${si.id}">Tagger ce site "Officiel Les amis de l'escalade !"</a></c:if>
      </c:if>
      <!-- if tag is present-->
      <c:if test="${si.officelSite}">
      <c:if test="${user.membreAssociation}"><a href="<%=pathWebcontent%>/deleteTagForOfficialSite/${si.id}">Enlever le tag "Officiel Les amis de l'escalade !"</a></c:if>
      </c:if>
      </p>

      <!-- Site action: delete site ** see only by member associative-->
      <p><c:if test="${user.membreAssociation}"><a href="<%=pathWebcontent%>/comfirmDeleteSite/${si.id}">supprimer le site</a></c:if></p>

      <!-- Site action: update site ** see only by member associative or user who create site-->
      <c:if test="${createur eq user.id or user.membreAssociation}">
      <p><a href="<%=pathWebcontent%>/updateSite/${si.id}">modifier le site</a></p>
      </c:if>

      <!-- Picture-->
      <p><img src="${si.urlPhotoSite}" alt="${si.urlPhotoSite}" style="width:500px;height:500px;border:0;"></p>
      <!-- lien pour crée un nouveau secteur-->
      <p><c:if test="${createur eq user.id or user.membreAssociation}"><a href="<%=pathWebcontent%>/createNewSector/${si.id}">Ajouter un nouveau secteur sur ce site </a></c:if></p>
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
        <c:if test="${createur eq user.id or user.membreAssociation}"><th>Ajout</th></c:if>
        <th>Commentaires</th>
        <c:if test="${user != null}"><th>Commentaires</th></c:if>
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
        <c:if test="${createur eq user.id or user.membreAssociation}"><td><a href="<%=pathWebcontent%>/createNewWay/${s.id}">Ajouter une nouvelle voie pour ce secteur</a></td></c:if>
        <td><a href="<%=pathWebcontent%>/commentRead/${s.element_id}">Voir</a></td>
        <c:if test="${user != null}"><td><a href="<%=pathWebcontent%>/commentWrite/${s.element_id}">Ecrire</a></td></c:if>
        <c:if test="${createur eq user.id or user.membreAssociation}">
          <td><a href="<%=pathWebcontent%>/updateSector/${s.id}">modifier le secteur</a></td>
        </c:if>
        <c:if test="${createur eq user.id or user.membreAssociation}">
          <td><a href="<%=pathWebcontent%>/deleteSector/${s.id}">supprimer le secteur</a></td>
        </c:if>
      </tr>
        </c:forEach>
      </table>
      </div>

      <h3>Les voies:</h3>

      <!-- Way pictures (depend of sectors number) -->
    <div class="sectorPictures">
        <p><c:forEach items="${secteur}" var="s">
      <img src="${s.urlPhotoSecteur}" alt="${s.urlPhotoSecteur}" style="width:500px;height:500px;border-width:1px;border-style:solid;border-color:black;">
        </c:forEach></p>
    </div>


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
        <th>commentaire</th>
        <c:if test="${user != null}"><th>commentaire</th></c:if>
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
          <td><a href="<%=pathWebcontent%>/commentRead/${v.element_id}">Voir</a></td>
          <c:if test="${user != null}"><td><a href="<%=pathWebcontent%>/commentWrite/${v.element_id}">Ecrire</a></td></c:if>
          <c:if test="${createur eq user.id or user.membreAssociation}">
            <td><a href="<%=pathWebcontent%>/updateWay/${v.id}">modifier la voie</a></td>
          </c:if>
          <c:if test="${createur eq user.id or user.membreAssociation}">
            <td><a href="<%=pathWebcontent%>/deleteWay/${v.id}">supprimer la voie</a></td>
          </c:if>
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
