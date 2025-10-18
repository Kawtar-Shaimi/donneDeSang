<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>DonnedeSang - Accueil</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-gray-800">

<jsp:include page="includes/navbar.jsp"/>

<section class="flex flex-col items-center justify-center text-center py-24 px-6">
    <h1 class="text-5xl font-extrabold text-red-700 mb-6">
        Sauvez des vies, un don à la fois ❤️
    </h1>
    <p class="text-lg text-gray-700 max-w-2xl mb-8">
        Bienvenue dans votre plateforme de gestion de banque de sang.
        Gérez les donneurs, les receveurs et optimisez la compatibilité pour sauver des vies.
    </p>
    <div class="flex gap-4">
        <a href="create-donneur.jsp" class="bg-red-600 text-white px-6 py-3 rounded-lg hover:bg-red-700 transition">Devenir Donneur</a>
        <a href="create-receveur.jsp" class="bg-gray-200 text-red-700 px-6 py-3 rounded-lg hover:bg-gray-300 transition">Besoin de Sang</a>
    </div>
</section>

<footer class="bg-gray-100 text-center py-4 mt-10 text-gray-600">
    © 2025 DonneDeSang | Conçu par Shaimi Kawtar
</footer>

</body>
</html>