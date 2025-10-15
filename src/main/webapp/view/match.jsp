<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Matching Donneurs - Receveurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6">

<div class="container mx-auto">

    <h1 class="text-3xl font-bold mb-6">Matching Donneurs - Receveurs</h1>

    <!-- Messages -->
    <c:if test="${not empty message}">
        <div class="mb-4 p-4 rounded ${messageType == 'success' ? 'bg-green-200 text-green-800' : 'bg-red-200 text-red-800'}">
                ${message}
        </div>
    </c:if>

    <!-- Sélecteur de receveur -->
    <form method="get" action="match" class="mb-6">
        <label for="receiverSelect" class="block mb-2 font-semibold">Sélectionner un receveur :</label>
        <select id="receiverSelect" name="receiverId" class="border rounded p-2 w-full max-w-md">
            <c:forEach var="receiver" items="${matches.keySet()}">
                <c:if test="${!receiver.satisfied}">
                    <option value="${receiver.id}">
                            ${receiver.firstName} ${receiver.lastName} - ${receiver.needType}
                    </option>
                </c:if>
            </c:forEach>
        </select>
        <button type="submit" class="mt-2 bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded">
            Afficher donneurs compatibles
        </button>
    </form>

    <!-- Table des donneurs compatibles -->
    <c:if test="${not empty param.receiverId}">
        <h2 class="text-xl font-bold mb-4">Donneurs compatibles et disponibles</h2>
        <table class="min-w-full bg-white border rounded shadow">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 border">Nom</th>
                <th class="py-2 px-4 border">Groupe sanguin</th>
                <th class="py-2 px-4 border">Statut</th>
                <th class="py-2 px-4 border">Poids</th>
                <th class="py-2 px-4 border">Âge</th>
                <th class="py-2 px-4 border">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donor" items="${matches[receiverDAO.findById(param.receiverId)]}">
                <tr class="text-center">
                    <td class="py-2 px-4 border">${donor.firstName} ${donor.lastName}</td>
                    <td class="py-2 px-4 border">${donor.bloodGroup}</td>
                    <td class="py-2 px-4 border">${donor.status}</td>
                    <td class="py-2 px-4 border">${donor.weight} kg</td>
                    <td class="py-2 px-4 border">${donor.age}</td>
                    <td class="py-2 px-4 border">
                        <form method="post" action="match">
                            <input type="hidden" name="donorId" value="${donor.id}" />
                            <input type="hidden" name="receiverId" value="${param.receiverId}" />
                            <button type="submit" class="bg-green-500 hover:bg-green-600 text-white px-3 py-1 rounded">
                                Associer
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>

</body>
</html>
