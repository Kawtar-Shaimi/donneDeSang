<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Receveurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-100 via-blue-200 to-blue-300 min-h-screen p-8">
<h1 class="text-4xl font-bold text-center text-blue-800 mb-8">Liste des Receveurs</h1>

<table class="min-w-full bg-white rounded-xl shadow-lg overflow-hidden">
    <thead class="bg-blue-500 text-white">
    <tr>
        <th class="px-4 py-2">Nom</th>
        <th class="px-4 py-2">Prénom</th>
        <th class="px-4 py-2">CIN</th>
        <th class="px-4 py-2">Téléphone</th>
        <th class="px-4 py-2">Date Naissance</th>
        <th class="px-4 py-2">Sexe</th>
        <th class="px-4 py-2">Groupe Sanguin</th>
        <th class="px-4 py-2">Besoin Poches</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${receveurs}">
        <tr class="even:bg-blue-100 odd:bg-blue-50 hover:bg-blue-200">
            <td class="border px-4 py-2">${r.nom}</td>
            <td class="border px-4 py-2">${r.prenom}</td>
            <td class="border px-4 py-2">${r.cin}</td>
            <td class="border px-4 py-2">${r.telephone}</td>
            <td class="border px-4 py-2">${r.dateNaissance}</td>
            <td class="border px-4 py-2">${r.sexe}</td>
            <td class="border px-4 py-2">${r.groupeSanguin}</td>
            <td class="border px-4 py-2">${r.besoinPoches}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>