package com.donnedesang.controller;

import com.donnedesang.model.Receveur;
import com.donnedesang.model.GroupeSanguin;
import com.donnedesang.model.Sexe;
import com.donnedesang.service.ReceveurService;
import com.donnedesang.service.DonneurService;
import com.donnedesang.model.Donneur;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReceveurServlet extends HttpServlet {

    private ReceveurService receveurService = new ReceveurService();
    private DonneurService donneurService = new DonneurService();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        List<Receveur> receveurs = receveurService.listerTousParPriorite();
        req.setAttribute("receveurs", receveurs);
        req.setAttribute("groupes", GroupeSanguin.values());
        req.setAttribute("sexes", Sexe.values());
        req.getRequestDispatcher("/listReceveurs.jsp").forward(req, resp);
    }

    // add receveur or associer donor (simple actions)
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addReceveur".equals(action)) {
            Receveur r = new Receveur();
            r.setNom(req.getParameter("nom"));
            r.setPrenom(req.getParameter("prenom"));
            r.setCin(req.getParameter("cin"));
            r.setTelephone(req.getParameter("telephone"));
            try { r.setDateNaissance(LocalDate.parse(req.getParameter("dateNaissance"))); } catch (Exception e) {}
            try { r.setSexe(com.donnedesang.model.Sexe.valueOf(req.getParameter("sexe"))); } catch (Exception e) {}
            try { r.setGroupeSanguin(com.donnedesang.model.GroupeSanguin.valueOf(req.getParameter("groupeSanguin"))); } catch (Exception e) {}
            try { r.setBesoinPoches(Integer.parseInt(req.getParameter("besoinPoches"))); } catch (Exception e) { r.setBesoinPoches(1); }
            receveurService.ajouterReceveur(r);
            resp.sendRedirect(req.getContextPath() + "/receveurs");
            return;
        }

        if ("associer".equals(action)) {
            // associer donneur <-> receveur
            Long idDonneur = null;
            Long idReceveur = null;
            try { idDonneur = Long.parseLong(req.getParameter("donneurId")); } catch (Exception e) {}
            try { idReceveur = Long.parseLong(req.getParameter("receveurId")); } catch (Exception e) {}

            Optional<Donneur> od = idDonneur == null ? Optional.empty() : donneurService.trouverParId(idDonneur);
            Optional<Receveur> or = idReceveur == null ? Optional.empty() : receveurService.trouverParId(idReceveur);

            if (od.isPresent() && or.isPresent()) {
                receveurService.associerDonneurAReceveur(od.get(), or.get());
            }
            resp.sendRedirect(req.getContextPath() + "/receveurs");
            return;
        }

        // default
        resp.sendRedirect(req.getContextPath() + "/receveurs");
    }
}