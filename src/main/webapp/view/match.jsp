<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Compatibilité - Don de Sang</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50">

<%@ include file="navbar.jsp" %>

<div class="max-w-5xl mx-auto mt-10 bg-white shadow-lg rounded-lg p-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">
        Compatibilité pour le receveur :
        <span class="text-red-600">${receiver.firstName} ${receiver.lastName}</span>
    </h2>

    <div class="bg-gray-100 p-4 rounded-lg mb-8">
        <p><strong>Groupe sanguin :</strong> ${receiver.bloodGroup}</p>
        <p><strong>Situation médicale :</strong> ${receiver.medicalStatus}</p>
        <p><strong>Nombre de poches requises :</strong> ${receiver.requiredBags}</p>
        <p><strong>Nombre actuel de donneurs :</strong> ${receiver.donors.size()}</p>
        <p><strong>État :</strong>
            <span class="${receiver.status == 'SATISFAIT' ? 'text-green-600' : 'text-yellow-600'}">
                ${receiver.status}
            </span>
        </p>
    </div>

    <h3 class="text-xl font-semibold text-gray-700 mb-4">Donneurs compatibles :</h3>

    <c:choose>
        <c:when test="${not empty compatibleDonors}">
            <table class="min-w-full bg-white border border-gray-200">
                <thead>
                <tr class="bg-gray-200 text-gray-600 uppercase text-sm">
                    <th class="py-3 px-4 text-left">Nom</th>
                    <th class="py-3 px-4 text-left">CIN</th>
                    <th class="py-3 px-4 text-left">Groupe sanguin</th>
                    <th class="py-3 px-4 text-left">Poids</th>
                    <th class="py-3 px-4 text-left">Éligibilité</th>
                    <th class="py-3 px-4 text-center">Action</th>
                </tr>
                </thead>
                <tbody class="text-gray-700">
                <c:forEach var="donor" items="${compatibleDonors}">
                    <tr class="border-b hover:bg-gray-100">
                        <td class="py-3 px-4">${donor.firstName} ${donor.lastName}</td>
                        <td class="py-3 px-4">${donor.cin}</td>
                        <td class="py-3 px-4">${donor.bloodGroup}</td>
                        <td class="py-3 px-4">${donor.weight} kg</td>
                        <td class="py-3 px-4">
                            <c:choose>
                                <c:when test="${!donor.hasContraindications && donor.status == 'DISPONIBLE'}">
                                    <span class="text-green-600 font-semibold">Éligible</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-red-600 font-semibold">Non éligible</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="py-3 px-4 text-center">
                            <form action="${pageContext.request.contextPath}/match" method="post">
                                <input type="hidden" name="donorId" value="${donor.id}">
                                <input type="hidden" name="receiverId" value="${receiver.id}">
                                <button type="submit"
                                        class="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700 transition">
                                    Associer
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-gray-600">Aucun donneur compatible trouvé pour ce receveur.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
