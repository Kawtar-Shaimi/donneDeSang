<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.donnedesang.model.Receiver" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Receveurs</title>

    <!-- TailwindCSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>

    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f8fafc;
        }
    </style>
</head>
<body class="min-h-screen flex flex-col items-center py-10">
<%@ include file="navbar.jsp" %>
<div class="w-full max-w-6xl bg-white rounded-2xl shadow-md p-8">
    <h1 class="text-3xl font-bold text-center text-red-600 mb-8">
        Gestion des Receveurs
    </h1>

    <!--  Formulaire d'ajout -->
    <div class="mb-10">
        <h2 class="text-xl font-semibold text-gray-700 mb-4">Ajouter un nouveau receveur</h2>

        <form action="${pageContext.request.contextPath}/receivers" method="post"
              class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

            <div>
                <label class="block text-gray-600 mb-1">Prénom</label>
                <input type="text" name="firstName"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Nom</label>
                <input type="text" name="lastName"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">CIN</label>
                <input type="text" name="cin"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Téléphone</label>
                <input type="text" name="phone"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Date de naissance</label>
                <input type="date" name="birthday"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Sexe</label>
                <select name="sexe"
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400">
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                </select>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Groupe sanguin</label>
                <select name="bloodGroup"
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
                    <option value="">Sélectionner</option>
                    <option>A+</option><option>A-</option>
                    <option>B+</option><option>B-</option>
                    <option>AB+</option><option>AB-</option>
                    <option>O+</option><option>O-</option>
                </select>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Type de besoin</label>
                <select name="needType"
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-red-400" required>
                    <option value="NORMAL">NORMAL</option>
                    <option value="URGENT">URGENT</option>
                    <option value="CRITIQUE">CRITIQUE</option>
                </select>
            </div>

            <div class="md:col-span-2 lg:col-span-3 text-center mt-4">
                <button type="submit"
                        class="bg-red-600 text-white font-medium px-6 py-2 rounded-lg hover:bg-red-700 transition">
                    Ajouter
                </button>
            </div>
        </form>
    </div>

    <!--  Tableau des receveurs -->
    <div>
        <h2 class="text-xl font-semibold text-gray-700 mb-4">Liste des receveurs</h2>

        <div class="overflow-x-auto">
            <table class="min-w-full border border-gray-200 rounded-lg overflow-hidden">
                <thead class="bg-red-600 text-white">
                <tr>
                    <th class="px-4 py-2 text-left">ID</th>
                    <th class="px-4 py-2 text-left">Nom complet</th>
                    <th class="px-4 py-2 text-left">CIN</th>
                    <th class="px-4 py-2 text-left">Téléphone</th>
                    <th class="px-4 py-2 text-left">Groupe</th>
                    <th class="px-4 py-2 text-left">Besoin</th>
                    <th class="px-4 py-2 text-left">Statut</th>
                    <th class="px-4 py-2 text-left">Actions</th>
                </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                <%
                    List<Receiver> receivers = (List<Receiver>) request.getAttribute("receivers");
                    if (receivers != null && !receivers.isEmpty()) {
                        for (Receiver r : receivers) {
                %>
                <tr class="hover:bg-gray-50 transition">
                    <td class="px-4 py-2"><%= r.getId() %></td>
                    <td class="px-4 py-2"><%= r.getFirstName() + " " + r.getLastName() %></td>
                    <td class="px-4 py-2"><%= r.getCin() %></td>
                    <td class="px-4 py-2"><%= r.getPhone() %></td>
                    <td class="px-4 py-2"><%= r.getBloodGroup() %></td>
                    <td class="px-4 py-2"><%= r.getNeedType() %></td>
                    <td class="px-4 py-2">
                        <%
                            boolean satisfied = r.isSatisfied();
                            String label = satisfied ? "Satisfait" : "En attente";
                            String color = satisfied ? "bg-green-500" : "bg-yellow-500";
                        %>
                        <span class="text-white text-sm px-3 py-1 rounded-full <%= color %>">
                            <%= label %>
                        </span>
                    </td>
                    <td class="px-4 py-2">
                        <form action="${pageContext.request.contextPath}/receivers" method="get">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= r.getId() %>">
                            <button type="submit" class="text-red-600 hover:underline">Modifier</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-center text-gray-500 py-4">Aucun receveur enregistré.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>