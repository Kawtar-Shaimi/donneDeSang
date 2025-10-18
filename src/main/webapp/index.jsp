<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>DonneDeSang - Accueil</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen">
<div class="container mx-auto p-6">
    <h1 class="text-3xl font-bold mb-4">Système de gestion - Banque de sang</h1>
    <div class="space-x-4">
        <a href="${pageContext.request.contextPath}/create.jsp" class="px-4 py-2 bg-blue-600 text-white rounded">Créer Donneur / Receveur</a>
        <a href="${pageContext.request.contextPath}/donors" class="px-4 py-2 bg-green-600 text-white rounded">Liste Donneurs</a>
        <a href="${pageContext.request.contextPath}/receveurs" class="px-4 py-2 bg-red-600 text-white rounded">Liste Receveurs</a>
    </div>
</div>
</body>
</html>