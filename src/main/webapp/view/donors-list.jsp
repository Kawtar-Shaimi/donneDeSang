<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.donnedesang.model.Donor" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Donneurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">

<%@ include file="navbar.jsp" %>

<div class="container mx-auto px-4 py-10">

    <h1 class="text-4xl font-bold text-center text-green-600 mb-10">Gestion des Donneurs</h1>

    <!-- Formulaire d'ajout d'un donneur -->
    <div class="bg-white rounded-2xl shadow-md p-6 mb-10">
        <h2 class="text-2xl font-semibold text-gray-700 mb-6">Ajouter un nouveau donneur</h2>
        <form action="${pageContext.request.contextPath}/donors" method="post" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

            <div>
                <label class="block text-gray-600 mb-1">Prénom</label>
                <input type="text" name="firstName" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Nom</label>
                <input type="text" name="lastName" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">CIN</label>
                <input type="text" name="cin" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Téléphone</label>
                <input type="text" name="phone" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Âge</label>
                <input type="number" name="age" min="18" max="65" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Poids (kg)</label>
                <input type="number" name="weight" step="0.1" min="40" required
                       class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Groupe sanguin</label>
                <select name="bloodGroup" required
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
                    <option value="">Sélectionner</option>
                    <option>A+</option><option>A-</option>
                    <option>B+</option><option>B-</option>
                    <option>AB+</option><option>AB-</option>
                    <option>O+</option><option>O-</option>
                </select>
            </div>

            <div>
                <label class="block text-gray-600 mb-1">Contre-indications</label>
                <select name="hasContraindications" required
                        class="w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-green-400">
                    <option value="false">Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>

            <div class="md:col-span-2 lg:col-span-3 text-center mt-4">
                <button type="submit"
                        class="bg-green-600 text-white font-medium px-6 py-2 rounded-lg hover:bg-green-700 transition">
                    Ajouter le donneur
                </button>
            </div>
        </form>
    </div>

    <!-- Tableau des donneurs -->
    <div class="bg-white rounded-2xl shadow-md p-6">
        <h2 class="text-2xl font-semibold text-gray-700 mb-4">Liste des donneurs</h2>

        <div class="overflow-x-auto">
            <table class="min-w-full border border-gray-200 rounded-lg overflow-hidden">
                <thead class="bg-green-600 text-white">
                <tr>
                    <th class="px-4 py-2 text-left">ID</th>
                    <th class="px-4 py-2 text-left">Nom complet</th>
                    <th class="px-4 py-2 text-left">CIN</th>
                    <th class="px-4 py-2 text-left">Téléphone</th>
                    <th class="px-4 py-2 text-left">Âge</th>
                    <th class="px-4 py-2 text-left">Poids (kg)</th>
                    <th class="px-4 py-2 text-left">Groupe</th>
                    <th class="px-4 py-2 text-left">Statut</th>
                    <th class="px-4 py-2 text-left">Contre-indications</th>
                    <th class="px-4 py-2 text-left">Actions</th>
                </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                <%
                    List<Donor> donors = (List<Donor>) request.getAttribute("donors");
                    if (donors != null && !donors.isEmpty()) {
                        for (Donor d : donors) {
                            // Définir la couleur du badge en fonction du statut
                            String statusColor = "bg-gray-400";
                            if ("DISPONIBLE".equals(d.getStatus())) {
                                statusColor = "bg-green-500";
                            } else if ("NON_DISPONIBLE".equals(d.getStatus())) {
                                statusColor = "bg-yellow-500";
                            } else if ("NON_ELIGIBLE".equals(d.getStatus())) {
                                statusColor = "bg-red-500";
                            }
                %>
                <tr class="hover:bg-gray-50 transition">
                    <td class="px-4 py-2"><%= d.getId() %></td>
                    <td class="px-4 py-2"><%= d.getFirstName() + " " + d.getLastName() %></td>
                    <td class="px-4 py-2"><%= d.getCin() %></td>
                    <td class="px-4 py-2"><%= d.getPhone() %></td>
                    <td class="px-4 py-2"><%= d.getAge() %></td>
                    <td class="px-4 py-2"><%= d.getWeight() %></td>
                    <td class="px-4 py-2"><%= d.getBloodGroup() %></td>
                    <td class="px-4 py-2">
                        <span class="text-white text-sm px-3 py-1 rounded-full <%= statusColor %>">
                            <%= d.getStatus() %>
                        </span>
                    </td>
                    <td class="px-4 py-2"><%= d.isHasContraindications() ? "Oui" : "Non" %></td>
                    <td class="px-4 py-2 space-x-2">
                        <form action="${pageContext.request.contextPath}/donors" method="get" class="inline">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= d.getId() %>">
                            <button type="submit" class="text-blue-600 hover:underline">Modifier</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/donors" method="get" class="inline">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= d.getId() %>">
                            <button type="submit" class="text-red-600 hover:underline"
                                    onclick="return confirm('Supprimer ce donneur ?');">Supprimer
                            </button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="10" class="text-center text-gray-500 py-4">Aucun donneur enregistré.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
