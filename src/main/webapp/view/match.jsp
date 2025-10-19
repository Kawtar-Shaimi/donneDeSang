<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.donnedesang.model.Receveur" %>
<%@ page import="com.donnedesang.model.Donneur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Match Donneurs - Receveurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-8">

<h1 class="text-3xl font-bold mb-6 text-center text-red-600">Match Donneurs / Receveurs</h1>

<c:if test="${not empty successMessage}">
    <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4 text-center">
            ${successMessage}
    </div>
</c:if>

<c:if test="${not empty errorMessage}">
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4 text-center">
            ${errorMessage}
    </div>
</c:if>

<table class="min-w-full bg-white rounded-lg shadow overflow-hidden">
    <thead class="bg-red-600 text-white">
    <tr>
        <th class="py-3 px-6">Receveur</th>
        <th class="py-3 px-6">Groupe</th>
        <th class="py-3 px-6">Urgence</th>
        <th class="py-3 px-6">État</th>
        <th class="py-3 px-6">Poches restantes</th>
        <th class="py-3 px-6">Donneurs Compatibles</th>
        <th class="py-3 px-6">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${receveurs}">
        <tr class="border-b hover:bg-gray-50">
            <td class="py-3 px-6">${r.nom} ${r.prenom}</td>
            <td class="py-3 px-6">${r.groupeSanguin.label}</td>
            <td class="py-3 px-6">
                <c:choose>
                    <c:when test="${r.besoinPoches == 4}">CRITIQUE</c:when>
                    <c:when test="${r.besoinPoches == 3}">URGENT</c:when>
                    <c:otherwise>NORMAL</c:otherwise>
                </c:choose>
            </td>
            <td class="py-3 px-6">
                <c:choose>
                    <c:when test="${r.satisfait}">SATISFAIT</c:when>
                    <c:otherwise>EN ATTENTE</c:otherwise>
                </c:choose>
            </td>
            <td class="py-3 px-6">${r.besoinPoches}</td>
            <td class="py-3 px-6">
                <form method="post" class="flex items-center gap-2">
                    <select name="donneurId" class="border rounded px-2 py-1">
                        <c:forEach var="d" items="${compatiblesMap[r.id]}">
                            <option value="${d.id}">${d.nom} ${d.prenom} (${d.groupeSanguin.label})</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="receveurId" value="${r.id}">
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white px-4 py-1 rounded">Associer</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>