<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>DonnedeSang - Accueil</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/framer-motion@10.16.4/dist/framer-motion.umd.js"></script>
</head>
<body class="bg-gray-50 text-gray-800 overflow-x-hidden">

<jsp:include page="view/navbar.jsp"/>

<!-- Section principale -->
<section class="relative flex flex-col items-center justify-center text-center py-28 px-6 min-h-screen overflow-hidden">

    <!-- Image de fond avec dégradé -->
    <div class="absolute inset-0 bg-cover bg-center"
         style="background-image: url('https://images.unsplash.com/photo-1603398938378-e54eab446dde?auto=format&fit=crop&w=1600&q=80');">
    </div>
    <div class="absolute inset-0 bg-gradient-to-r from-red-900/70 via-red-800/60 to-red-500/50"></div>

    <!-- Contenu -->
    <div class="relative z-10 text-white max-w-3xl animate-fadeIn">
        <h1 class="text-5xl md:text-6xl font-extrabold mb-6 drop-shadow-lg">
            <span id="heroText">Sauvez des vies, un don à la fois ❤️</span>
        </h1>
        <p class="text-lg md:text-xl text-gray-100 mb-8 leading-relaxed">
            Bienvenue sur votre plateforme de gestion de banque de sang.<br>
            Gérez les donneurs, les receveurs et facilitez la compatibilité pour sauver des vies.
        </p>

        <div class="flex flex-col sm:flex-row justify-center gap-4">
            <a href="${pageContext.request.contextPath}/view/create-donneur.jsp"
               class="bg-red-600 hover:bg-red-700 text-white px-8 py-3 rounded-lg font-semibold shadow-md transform hover:scale-105 transition">
                ❤️ Devenir Donneur
            </a>
            <a href="${pageContext.request.contextPath}/view/create-receveur.jsp"
               class="bg-white text-red-700 hover:bg-gray-100 px-8 py-3 rounded-lg font-semibold shadow-md transform hover:scale-105 transition">
                🩸 Besoin de Sang
            </a>
        </div>
    </div>

    <!-- Animation décorative -->
    <div class="absolute bottom-0 w-full overflow-hidden leading-[0]">
        <svg class="relative block w-full h-24" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120"
             preserveAspectRatio="none">
            <path d="M0,0V46.29c47.25,22.93,98,29.32,146,8C230.29,23.09,284,3.24,339.76,3.24c57.64,0,113.43,20.75,169.91,36.08,48.58,13.23,95.18,27.86,143.74,32.31,59.88,5.53,119.23-4.85,177.58-18.29,86.46-20.19,172.15-48.71,257.01-25.44V0Z"
                  opacity=".25" class="fill-white"></path>
            <path d="M0,0V15.81C47.42,36.62,98.08,44.78,146,33.43c54.48-12.95,108.4-42.14,162.68-39.31,54.14,2.83,106.49,38.45,161.27,50.7,52.3,11.72,104.46-2.75,156.57-15.88C725.44,13.86,780,3.72,837.56,6.59c51.72,2.67,101.7,19.85,152.59,29.43C1043.92,46.85,1096.08,49.25,1148,33.91V0Z"
                  opacity=".5" class="fill-white"></path>
            <path d="M0,0V5.63C47.42,27.17,98.08,42.15,146,38.83,200.48,34.85,254.4,10.43,308.68,7.24c54.14-3.18,106.49,16.91,161.27,29.71,52.3,12.08,104.46,11.36,156.57,1.24C725.44,25.39,780,0,837.56,0c51.72,0,101.7,17.27,152.59,25.64C1043.92,30.52,1096.08,31.79,1148,22.67V0Z"
                  class="fill-white"></path>
        </svg>
    </div>
</section>

<!-- Section infos -->
<section class="py-16 px-8 bg-white text-center">
    <h2 class="text-3xl font-bold text-red-700 mb-8">Pourquoi donner du sang ?</h2>

    <div class="grid md:grid-cols-3 gap-8 max-w-6xl mx-auto">
        <div class="p-6 rounded-xl shadow-md bg-red-50 hover:bg-red-100 transition transform hover:-translate-y-1">
            <h3 class="text-xl font-semibold mb-3 text-red-700">💓 Sauver des vies</h3>
            <p>Un seul don peut sauver jusqu’à trois vies. Votre geste compte, à chaque instant.</p>
        </div>
        <div class="p-6 rounded-xl shadow-md bg-red-50 hover:bg-red-100 transition transform hover:-translate-y-1">
            <h3 class="text-xl font-semibold mb-3 text-red-700">🧬 Solidarité</h3>
            <p>En donnant, vous soutenez ceux qui traversent des moments critiques et urgents.</p>
        </div>
        <div class="p-6 rounded-xl shadow-md bg-red-50 hover:bg-red-100 transition transform hover:-translate-y-1">
            <h3 class="text-xl font-semibold mb-3 text-red-700">🌍 Communauté</h3>
            <p>Ensemble, construisons un réseau solidaire et fort pour répondre aux besoins vitaux.</p>
        </div>
    </div>
</section>

<footer class="bg-gray-100 text-center py-6 mt-10 text-gray-600">
    © 2025 <span class="font-semibold text-red-600">DonneDeSang</span> | Conçu par Shaimi Kawtar 💖
</footer>

<script>
    // Animation de texte dynamique
    const phrases = [
        "Sauvez des vies, un don à la fois ❤️",
        "Chaque goutte compte 🩸",
        "La vie, c’est le plus beau des dons 💝"
    ];
    let i = 0;
    const heroText = document.getElementById("heroText");

    setInterval(() => {
        i = (i + 1) % phrases.length;
        heroText.classList.add("opacity-0", "transition-opacity", "duration-700");
        setTimeout(() => {
            heroText.textContent = phrases[i];
            heroText.classList.remove("opacity-0");
        }, 700);
    }, 4000);
</script>

</body>
</html>