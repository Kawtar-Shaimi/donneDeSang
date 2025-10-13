<%@ page contentType="text/html; charset=UTF-8" %>

<!--  Barre de navigation principale -->
<nav class="w-full bg-blue-600 text-white py-3 shadow-md">
    <div class="max-w-6xl mx-auto flex justify-between items-center px-6">
        <h1 class="text-xl font-bold"> Don de Sang</h1>
        <ul class="flex space-x-6">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp"
                   class="hover:text-gray-200 transition">Accueil</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/donors"
                   class="hover:text-gray-200 transition">Donneurs</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/receivers"
                   class="hover:text-gray-200 transition">Receveurs</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/match"
                   class="hover:text-gray-200 transition">Compatibilité</a>
            </li>
        </ul>
    </div>
</nav>
