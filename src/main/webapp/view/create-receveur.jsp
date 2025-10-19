<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Receveur</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">

<jsp:include page="navbar.jsp"/>

<div class="max-w-3xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
    <h2 class="text-3xl font-bold text-red-700 mb-6 text-center">💉 Ajouter un Receveur</h2>

    <form action="${pageContext.request.contextPath}/receveurs" method="post" class="space-y-5">
        <div class="grid grid-cols-2 gap-4">
            <input type="text" name="nom" placeholder="Nom" required class="border rounded-lg px-4 py-2">
            <input type="text" name="prenom" placeholder="Prénom" required class="border rounded-lg px-4 py-2">
            <input type="text" name="cin" placeholder="CIN" required class="border rounded-lg px-4 py-2">
            <input type="text" name="telephone" placeholder="Téléphone" required class="border rounded-lg px-4 py-2">
        </div>

        <div class="grid grid-cols-2 gap-4">
            <input type="date" name="dateNaissance" required class="border rounded-lg px-4 py-2">
            <select name="sexe" required class="border rounded-lg px-4 py-2">
                <option value="">-- Sexe --</option>
                <option value="MASCULIN">Homme</option>
                <option value="FEMININ">Femme</option>
            </select>
        </div>

        <div class="grid grid-cols-2 gap-4">
            <select name="groupeSanguin" required class="border rounded-lg px-4 py-2">
                <option value="">-- Groupe Sanguin --</option>
                <option value="O_NEGATIF">O-</option>
                <option value="O_POSITIF">O+</option>
                <option value="A_NEGATIF">A-</option>
                <option value="A_POSITIF">A+</option>
                <option value="B_NEGATIF">B-</option>
                <option value="B_POSITIF">B+</option>
                <option value="AB_NEGATIF">AB-</option>
                <option value="AB_POSITIF">AB+</option>
            </select>

            <select name="situation" required class="border rounded-lg px-4 py-2">
                <option value="">-- Situation médicale --</option>
                <option value="CRITIQUE">Critique (4 poches)</option>
                <option value="URGENT">Urgent (3 poches)</option>
                <option value="NORMAL">Normal (1 poche)</option>
            </select>
        </div>

        <button type="submit" class="w-full bg-red-600 text-white py-2 rounded-lg hover:bg-red-700 transition">
            Enregistrer le Receveur
        </button>
    </form>
</div>

</body>
</html>