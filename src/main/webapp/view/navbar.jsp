<!-- Barre de navigation principale -->
<nav class="w-full bg-white shadow-md border-b border-gray-200">
    <div class="max-w-7xl mx-auto flex justify-between items-center px-6 py-3">
        <!-- Logo / Titre -->
        <h1 class="text-2xl font-bold text-green-600">Don de Sang</h1>

        <!-- Liens de navigation -->
        <ul class="flex space-x-8 text-gray-700 font-medium">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp"
                   class="hover:text-green-600 transition duration-200">Accueil</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/donors"
                   class="hover:text-green-600 transition duration-200">Donneurs</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/receivers"
                   class="hover:text-green-600 transition duration-200">Receveurs</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/match"
                   class="hover:text-green-600 transition duration-200">Compatibilité</a>
            </li>
        </ul>

        <!-- Bouton ou logo à droite -->
        <a href="${pageContext.request.contextPath}/donors?action=add"
           class="bg-green-600 text-white px-4 py-2 rounded-xl shadow hover:bg-green-700 transition duration-200">
            + Nouveau Donneur
        </a>
    </div>
</nav>
