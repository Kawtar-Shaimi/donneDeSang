<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Donneurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
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
        <th class="px-4 py-2 text-center">Action</th>
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
            <td class="border px-4 py-2 font-bold">
                <c:choose>
                    <c:when test="${d.statut eq 'DISPONIBLE'}">
                        <span class="text-green-600">${d.statut}</span>
                    </c:when>
                    <c:when test="${d.statut eq 'NON_DISPONIBLE'}">
                        <span class="text-yellow-600">${d.statut}</span>
                    </c:when>
                    <c:when test="${d.statut eq 'NON_ELIGIBLE'}">
                        <span class="text-red-600">${d.statut}</span>
                    </c:when>
                    <c:otherwise>${d.statut}</c:otherwise>
                </c:choose>
            </td>
            <td class="border px-4 py-2">
                <c:if test="${d.hepatiteB}">HepB </c:if>
                <c:if test="${d.hepatiteC}">HepC </c:if>
                <c:if test="${d.vih}">VIH </c:if>
                <c:if test="${d.diabete}">Diabète </c:if>
                <c:if test="${d.grossesse}">Grossesse </c:if>
                <c:if test="${d.allaitement}">Allaitement</c:if>
            </td>
            <td class="border px-4 py-2 text-center">
                <button onclick="openModal('${d.id}', '${d.nom}', '${d.prenom}', '${d.cin}', '${d.telephone}', '${d.dateNaissance}', '${d.sexe}', '${d.groupeSanguin}', '${d.poids}')"
                        class="text-red-600 hover:text-red-800">
                    <i data-lucide="edit"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- MODAL -->
<div id="editModal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center hidden">
    <div class="bg-white p-6 rounded-lg shadow-lg w-1/2 relative">
        <h2 class="text-2xl font-bold mb-4 text-red-600 text-center">Modifier Donneur</h2>
        <form method="post" class="space-y-3">
            <input type="hidden" name="id" id="editId">

            <div>
                <label>Nom :</label>
                <input type="text" id="editNom" name="nom" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>Prénom :</label>
                <input type="text" id="editPrenom" name="prenom" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>CIN :</label>
                <input type="text" id="editCin" name="cin" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>Téléphone :</label>
                <input type="text" id="editTel" name="telephone" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>Date de naissance :</label>
                <input type="date" id="editDate" name="dateNaissance" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>Poids :</label>
                <input type="number" id="editPoids" name="poids" class="border p-2 w-full rounded">
            </div>

            <div>
                <label>Sexe :</label>
                <select id="editSexe" name="sexe" class="border p-2 w-full rounded">
                    <option value="MASCULIN">Masculin</option>
                    <option value="FEMININ">Féminin</option>
                </select>
            </div>

            <div>
                <label>Groupe sanguin :</label>
                <select id="editGroupe" name="groupeSanguin" class="border p-2 w-full rounded">
                    <c:forEach var="g" items="${groupes}">
                        <option value="${g}">${g}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="flex justify-end gap-2 mt-4">
                <button type="button" onclick="closeModal()" class="bg-gray-400 text-white px-4 py-2 rounded">Annuler</button>
                <button type="submit" class="bg-red-600 text-white px-4 py-2 rounded">Modifier</button>
            </div>
        </form>
    </div>
</div>

<script>
    lucide.createIcons();

    function openModal(id, nom, prenom, cin, tel, date, sexe, groupe, poids) {
        document.getElementById("editId").value = id;
        document.getElementById("editNom").value = nom;
        document.getElementById("editPrenom").value = prenom;
        document.getElementById("editCin").value = cin;
        document.getElementById("editTel").value = tel;
        document.getElementById("editDate").value = date;
        document.getElementById("editPoids").value = poids;
        document.getElementById("editSexe").value = sexe;
        document.getElementById("editGroupe").value = groupe;
        document.getElementById("editModal").classList.remove("hidden");
    }

    function closeModal() {
        document.getElementById("editModal").classList.add("hidden");
    }

    // Fermer le modal en cliquant à l’extérieur
    window.addEventListener("click", function(event) {
        const modal = document.getElementById("editModal");
        if (event.target === modal) {
            closeModal();
        }
    });
</script>

</body>
</html>