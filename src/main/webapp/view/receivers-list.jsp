<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Receveurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light p-4">

<div class="container">
    <h2 class="text-center mb-4">Liste des Receveurs</h2>

    <a href="create-receiver.jsp" class="btn btn-primary mb-3">➕ Ajouter un Receveur</a>

    <table class="table table-bordered table-hover bg-white">
        <thead class="table-dark">
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>CIN</th>
            <th>Téléphone</th>
            <th>Groupe Sanguin</th>
            <th>Besoin</th>
            <th>Statut</th>
            <th>Donneur associé</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="r" items="${receivers}">
            <tr>
                <td>${r.lastName}</td>
                <td>${r.firstName}</td>
                <td>${r.cin}</td>
                <td>${r.phone}</td>
                <td>${r.bloodGroup}</td>
                <td>${r.needType}</td>
                <td>
                    <c:choose>
                        <c:when test="${r.satisfied}">✅ Satisfait</c:when>
                        <c:otherwise>🕓 En attente</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${r.donor != null}">
                        ${r.donor.firstName} ${r.donor.lastName}
                    </c:if>
                    <c:if test="${r.donor == null}">
                        Aucun
                    </c:if>
                </td>
                <td>
                    <a href="receivers?action=edit&id=${r.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="receivers?action=delete&id=${r.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Supprimer ce receveur ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
