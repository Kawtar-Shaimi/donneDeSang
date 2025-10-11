<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.donnedesang.model.Donor" %>

<%
    Donor donor = (Donor) request.getAttribute("donor");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Donneur</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 py-10 flex justify-center">

<div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl">
    <h2 class="text-2xl font-bold mb-6 text-center text-blue-600">Modifier le Donneur</h2>

    <form action="${pageContext.request.contextPath}/donors" method="post" class="space-y-4">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= donor.getId() %>">

        <div>
            <label>Prénom</label>
            <input type="text" name="firstName" value="<%= donor.getFirstName() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>Nom</label>
            <input type="text" name="lastName" value="<%= donor.getLastName() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>CIN</label>
            <input type="text" name="cin" value="<%= donor.getCin() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>Téléphone</label>
            <input type="text" name="phone" value="<%= donor.getPhone() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>Âge</label>
            <input type="number" name="age" value="<%= donor.getAge() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>Poids</label>
            <input type="number" name="weight" value="<%= donor.getWeight() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div>
            <label>Groupe sanguin</label>
            <input type="text" name="bloodGroup" value="<%= donor.getBloodGroup() %>"
                   class="w-full border p-2 rounded-lg" required>
        </div>

        <div class="text-center mt-6">
            <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700">
                 Enregistrer
            </button>
            <a href="${pageContext.request.contextPath}/donors" class="ml-4 text-gray-600 hover:underline">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>