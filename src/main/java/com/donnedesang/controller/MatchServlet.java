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
import java.util.List;
import java.util.Optional;

public class MatchServlet extends HttpServlet {

    private final DonneurService donneurService = new DonneurService();
    private final ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Liste de tous les receveurs non satisfaits
        List<Receveur> receveurs = receveurService.listerTousParPriorite()
                .stream().filter(r -> !r.isSatisfait()).toList();

        // Liste de tous les donneurs disponibles
        List<Donneur> donneurs = donneurService.listerTous()
                .stream().filter(d -> d.getStatut().name().equals("DISPONIBLE")).toList();

        req.setAttribute("receveurs", receveurs);
        req.setAttribute("donneurs", donneurs);

        req.getRequestDispatcher("/view/match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long idReceveur = Long.parseLong(req.getParameter("receveurId"));
        Long idDonneur = Long.parseLong(req.getParameter("donneurId"));

        Optional<Donneur> donneurOpt = donneurService.trouverParId(idDonneur);
        Optional<Receveur> receveurOpt = receveurService.trouverParId(idReceveur);

        if (donneurOpt.isPresent() && receveurOpt.isPresent()) {
            Receveur r = receveurOpt.get();
            Donneur d = donneurOpt.get();

            // Vérifie compatibilité et disponibilité
            if (d.getStatut().name().equals("DISPONIBLE")
                    && !r.isSatisfait()
                    && com.donnedesang.model.GroupeSanguin.estCompatible(d.getGroupeSanguin(), r.getGroupeSanguin())) {

                receveurService.associerDonneurAReceveur(d, r);
                req.setAttribute("message", " Donneur associé avec succès !");
            } else {
                req.setAttribute("error", " Incompatibilité ou receveur déjà satisfait !");
            }
        }

        doGet(req, resp);
    }
}