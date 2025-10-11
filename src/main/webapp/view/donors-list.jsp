<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.donnedesang.model.Donor" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Donneurs</title>

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

<div class="w-full max-w-6xl bg-white rounded-2xl shadow-md p-8">
    <h1 class="text-3xl font-bold text-center text-blue-600 mb-8">
        Gestion des Donneurs
    </h1>

    <!-- ✅ Formulaire d'ajout -->
    <div class="mb-10">
        <h2 class="text-xl font-semibold text-gray-700 mb-4">Ajouter un nouveau donneur</h2>

        <form action="${pageContext.request.contextPath}/donors" method="post"
              class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

            <div>
                <label class="block text-gray-600 mb-1">Prénom</label>
                <input type="text" name="firstName"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Nom</label>
                <input type="text" name="lastName"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">CIN</label>
                <input type="text" name="cin"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Téléphone</label>
                <input type="text" name="phone"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Âge</label>
                <input type="number" name="age"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Poids (kg)</label>
                <input type="number" name="weight"
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Groupe sanguin</label>
                <select name="bloodGroup"
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
                    <option value="">Sélectionner</option>
                    <option>A+</option><option>A-</option>
                    <option>B+</option><option>B-</option>
                    <option>AB+</option><option>AB-</option>
                    <option>O+</option><option>O-</option>
                </select>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Contre-indications</label>
                <select name="hasContraindications"
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required>
                    <option value="no">Aucune</option>
                    <option value="yes">Oui</option>
                </select>
            </div>

            <div class="md:col-span-2 lg:col-span-3 text-center mt-4">
                <button type="submit"
                        class="bg-blue-600 text-white font-medium px-6 py-2 rounded-lg hover:bg-blue-700 transition">
                    ➕ Ajouter
                </button>
            </div>
        </form>
    </div>

    <!-- ✅ Tableau des donneurs -->
    <div>
        <h2 class="text-xl font-semibold text-gray-700 mb-4">Liste des donneurs</h2>

        <div class="overflow-x-auto">
            <table class="min-w-full border border-gray-200 rounded-lg overflow-hidden">
                <thead class="bg-blue-600 text-white">
                <tr>
                    <th class="px-4 py-2 text-left">ID</th>
                    <th class="px-4 py-2 text-left">Nom complet</th>
                    <th class="px-4 py-2 text-left">CIN</th>
                    <th class="px-4 py-2 text-left">Téléphone</th>
                    <th class="px-4 py-2 text-left">Groupe</th>
                    <th class="px-4 py-2 text-left">Âge</th>
                    <th class="px-4 py-2 text-left">Poids</th>
                    <th class="px-4 py-2 text-left">Statut</th>
                    <th class="px-4 py-2 text-left">Actions</th>
                </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                <%
                    List<Donor> donors = (List<Donor>) request.getAttribute("donors");
                    if (donors != null && !donors.isEmpty()) {
                        for (Donor d : donors) {
                %>
                <tr class="hover:bg-gray-50 transition">
                    <td class="px-4 py-2"><%= d.getId() %></td>
                    <td class="px-4 py-2"><%= d.getFirstName() + " " + d.getLastName() %></td>
                    <td class="px-4 py-2"><%= d.getCin() %></td>
                    <td class="px-4 py-2"><%= d.getPhone() %></td>
                    <td class="px-4 py-2"><%= d.getBloodGroup() %></td>
                    <td class="px-4 py-2"><%= d.getAge() %></td>
                    <td class="px-4 py-2"><%= d.getWeight() %></td>
                    <td class="px-4 py-2">
                        <%
                            String status = d.getStatus();
                            String color = "bg-gray-300";
                            if ("DISPONIBLE".equals(status)) color = "bg-green-500";
                            else if ("NON_DISPONIBLE".equals(status)) color = "bg-yellow-500";
                            else if ("NON_ELIGIBLE".equals(status)) color = "bg-red-500";
                        %>
                        <span class="text-white text-sm px-3 py-1 rounded-full <%= color %>">
                                    <%= status %>
                                </span>
                    </td>
                    <td class="px-4 py-2">
                        <form action="${pageContext.request.contextPath}/donors" method="get">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= d.getId() %>">
                            <button type="submit" class="text-blue-600 hover:underline">Modifier</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-center text-gray-500 py-4">Aucun donneur enregistré.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
