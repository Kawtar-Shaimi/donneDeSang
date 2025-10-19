package com.donnedesang.controller;

import com.donnedesang.model.Donneur;
import com.donnedesang.model.Receveur;
import com.donnedesang.service.DonneurService;
import com.donnedesang.service.ReceveurService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchServlet extends HttpServlet {

    private final DonneurService donneurService = new DonneurService();
    private final ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Receveur> receveurs = receveurService.listerTousParPriorite();

        // Map receveurId -> donneurs compatibles
        Map<Long, List<Donneur>> compatiblesMap = new HashMap<>();
        for (Receveur r : receveurs) {
            List<Donneur> compatibles = donneurService.listerDisponiblesCompatibles(r.getGroupeSanguin());
            compatiblesMap.put(r.getId(), compatibles);
        }

        req.setAttribute("receveurs", receveurs);
        req.setAttribute("compatiblesMap", compatiblesMap);

        // Messages
        String successMessage = req.getParameter("successMessage");
        String errorMessage = req.getParameter("errorMessage");
        if (successMessage != null) req.setAttribute("successMessage", successMessage);
        if (errorMessage != null) req.setAttribute("errorMessage", errorMessage);

        req.getRequestDispatcher("/view/match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String receveurIdStr = req.getParameter("receveurId");
        String donneurIdStr = req.getParameter("donneurId");

        if (receveurIdStr == null || donneurIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/match?errorMessage=Veuillez sélectionner un donneur !");
            return;
        }

        try {
            Long receveurId = Long.parseLong(receveurIdStr);
            Long donneurId = Long.parseLong(donneurIdStr);

            Receveur receveur = receveurService.trouverParId(receveurId).orElse(null);
            Donneur donneur = donneurService.trouverParId(donneurId).orElse(null);

            if (receveur != null && donneur != null) {
                boolean associe = receveurService.associerDonneurAReceveur(donneur, receveur);
                if (associe) {
                    //resp.sendRedirect(req.getContextPath() + "/match?successMessage=Donneur associé avec succès !");
                    String message = URLEncoder.encode("Donneur associé avec succès !", StandardCharsets.UTF_8);
                    resp.sendRedirect(req.getContextPath() + "/match?successMessage=" + message);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/match?errorMessage=Impossible d'associer ce donneur !");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/match?errorMessage=Erreur lors de l'association !");
        }
    }
}