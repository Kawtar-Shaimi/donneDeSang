<!-- Barre de navigation principale -->
<nav class="w-full bg-white shadow-md border-b border-gray-200">
    <div class="max-w-7xl mx-auto flex justify-between items-center px-6 py-3">
        <!-- Logo / Titre -->
        <h1 class="text-2xl font-bold text-black">Don de Sang</h1>

        <!-- Liens de navigation -->
        <ul class="flex space-x-8 text-gray-700 font-medium">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp"
                   class="hover:text-black transition duration-200">Accueil</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/donors"
                   class="hover:text-black transition duration-200">Donneurs</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/receivers"
                   class="hover:text-black transition duration-200">Receveurs</a>
            </li>

            <li>
                <!-- Redirection vers la page d'affichage des compatibilités -->
                <a href="${pageContext.request.contextPath}/matchView.jsp"
                   class="hover:text-green-600 transition duration-200">Compatibilite</a>
            </li>
        </ul>
    </div>
</nav>