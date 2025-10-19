<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Donneurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-red-100 via-red-200 to-red-300 min-h-screen p-8">
<h1 class="text-4xl font-bold text-center text-red-800 mb-8">Liste des Donneurs</h1>

<table class="min-w-full bg-white rounded-xl shadow-lg overflow-hidden">
    <thead class="bg-red-500 text-white">
    <tr>
        <th class="px-4 py-2">Nom</th>
        <th class="px-4 py-2">Prénom</th>
        <th class="px-4 py-2">CIN</th>
        <th class="px-4 py-2">Téléphone</th>
        <th class="px-4 py-2">Date Naissance</th>
        <th class="px-4 py-2">Sexe</th>
        <th class="px-4 py-2">Groupe Sanguin</th>
        <th class="px-4 py-2">Poids</th>
        <th class="px-4 py-2">Statut</th>
        <th class="px-4 py-2">Pathologies</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${donneurs}">
        <tr class="even:bg-red-100 odd:bg-red-50 hover:bg-red-200">
            <td class="border px-4 py-2">${d.nom}</td>
            <td class="border px-4 py-2">${d.prenom}</td>
            <td class="border px-4 py-2">${d.cin}</td>
            <td class="border px-4 py-2">${d.telephone}</td>
            <td class="border px-4 py-2">${d.dateNaissance}</td>
            <td class="border px-4 py-2">${d.sexe}</td>
            <td class="border px-4 py-2">${d.groupeSanguin}</td>
            <td class="border px-4 py-2">${d.poids}</td>
            <td class="border px-4 py-2">${d.statut}</td>
            <td class="border px-4 py-2">
                <c:if test="${d.hepatiteB}">HepB </c:if>
                <c:if test="${d.hepatiteC}">HepC </c:if>
                <c:if test="${d.vih}">VIH </c:if>
                <c:if test="${d.diabete}">Diabète </c:if>
                <c:if test="${d.grossesse}">Grossesse </c:if>
                <c:if test="${d.allaitement}">Allaitement</c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>