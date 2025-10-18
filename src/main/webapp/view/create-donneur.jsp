<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Donneur</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">

<jsp:include page="navbar.jsp"/>

<div class="max-w-3xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
    <h2 class="text-3xl font-bold text-red-700 mb-6 text-center">🩸 Ajouter un Donneur</h2>

    <form action="donneurs" method="post" class="space-y-5">
        <div class="grid grid-cols-2 gap-4">
            <input type="text" name="nom" placeholder="Nom" required class="border rounded-lg px-4 py-2">
            <input type="text" name="prenom" placeholder="Prénom" required class="border rounded-lg px-4 py-2">
            <input type="text" name="cin" placeholder="CIN" required class="border rounded-lg px-4 py-2">
            <input type="text" name="telephone" placeholder="Téléphone" required class="border rounded-lg px-4 py-2">
        </div>

        <div class="grid grid-cols-2 gap-4">
            <input type="date" name="dateNaissance" required class="border rounded-lg px-4 py-2">
            <input type="number" name="poids" placeholder="Poids (kg)" required class="border rounded-lg px-4 py-2">
        </div>

        <div class="grid grid-cols-2 gap-4">
            <select name="sexe" required class="border rounded-lg px-4 py-2">
                <option value="">-- Sexe --</option>
                <option value="HOMME">Homme</option>
                <option value="FEMME">Femme</option>
            </select>

            <select name="groupeSanguin" required class="border rounded-lg px-4 py-2">
                <option value="">-- Groupe Sanguin --</option>
                <option>O-</option><option>O+</option>
                <option>A-</option><option>A+</option>
                <option>B-</option><option>B+</option>
                <option>AB-</option><option>AB+</option>
            </select>
        </div>

        <div class="grid grid-cols-2 gap-4">
            <label class="flex items-center gap-2"><input type="checkbox" name="hepatiteB"> Hépatite B</label>
            <label class="flex items-center gap-2"><input type="checkbox" name="hepatiteC"> Hépatite C</label>
            <label class="flex items-center gap-2"><input type="checkbox" name="vih"> VIH</label>
            <label class="flex items-center gap-2"><input type="checkbox" name="diabete"> Diabète</label>
            <label class="flex items-center gap-2"><input type="checkbox" name="grossesse"> Grossesse</label>
            <label class="flex items-center gap-2"><input type="checkbox" name="allaitement"> Allaitement</label>
        </div>

        <button type="submit" class="w-full bg-red-600 text-white py-2 rounded-lg hover:bg-red-700 transition">
            Enregistrer le Donneur
        </button>
    </form>
</div>

</body>
</html>