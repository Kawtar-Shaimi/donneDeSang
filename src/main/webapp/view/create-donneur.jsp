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

<div class="relative z-10 flex flex-col items-center justify-center py-12 px-4">
    <div class="w-full max-w-3xl bg-white/90 backdrop-blur-lg shadow-2xl rounded-2xl p-10 border border-red-100">
        <h2 class="text-4xl font-extrabold text-center text-red-700 mb-6">
            🩸 Ajouter un Donneur
        </h2>
        <p class="text-center text-gray-600 mb-8">
            Remplissez les informations ci-dessous pour enregistrer un nouveau donneur de sang.
        </p>

        <form onsubmit="handleSubmit(event)"
              action="${pageContext.request.contextPath}/donors" method="post"
              class="space-y-6">

            <!-- Infos personnelles -->
            <div class="grid grid-cols-2 gap-5">
                <input type="text" name="nom" placeholder="Nom" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
                <input type="text" name="prenom" placeholder="Prénom" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
                <input type="text" name="cin" placeholder="CIN" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
                <input type="text" name="telephone" placeholder="Téléphone" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
            </div>

            <!-- Date et poids -->
            <div class="grid grid-cols-2 gap-5">
                <input type="date" name="dateNaissance" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
                <input type="number" name="poids" placeholder="Poids (kg)" required
                       class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
            </div>

            <!-- Sexe & Groupe sanguin -->
            <div class="grid grid-cols-2 gap-5">
                <select name="sexe" required
                        class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
                    <option value="">-- Sexe --</option>
                    <option value="MASCULIN">Homme</option>
                    <option value="FEMININ">Femme</option>
                </select>

                <select name="groupeSanguin" required
                        class="border rounded-xl px-4 py-2.5 focus:ring-2 focus:ring-red-500 transition w-full">
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
            </div>

            <!-- Pathologies -->
            <div class="bg-red-50 p-4 rounded-xl border border-red-200">
                <h3 class="text-red-700 font-semibold mb-3">Pathologies / Conditions :</h3>
                <div class="grid grid-cols-2 gap-3 text-gray-700">
                    <label class="flex items-center gap-2"><input type="checkbox" name="hepatiteB" class="accent-red-600"> Hépatite B</label>
                    <label class="flex items-center gap-2"><input type="checkbox" name="hepatiteC" class="accent-red-600"> Hépatite C</label>
                    <label class="flex items-center gap-2"><input type="checkbox" name="vih" class="accent-red-600"> VIH</label>
                    <label class="flex items-center gap-2"><input type="checkbox" name="diabete" class="accent-red-600"> Diabète</label>
                    <label class="flex items-center gap-2"><input type="checkbox" name="grossesse" class="accent-red-600"> Grossesse</label>
                    <label class="flex items-center gap-2"><input type="checkbox" name="allaitement" class="accent-red-600"> Allaitement</label>
                </div>
            </div>

            <!-- bouton -->
            <button id="submitBtn" type="submit"
                    class="w-full bg-red-600 text-white py-3 rounded-xl font-semibold hover:bg-red-700 shadow-lg transition-all duration-300">
                 Enregistrer le Donneur
            </button>
        </form>
    </div>
</div>

</body>
</html>