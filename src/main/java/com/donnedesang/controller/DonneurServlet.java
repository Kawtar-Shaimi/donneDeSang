package com.donnedesang.controller;

import com.donnedesang.model.Donneur;
import com.donnedesang.model.GroupeSanguin;
import com.donnedesang.model.Sexe;
import com.donnedesang.model.StatutDisponibilite;
import com.donnedesang.service.DonneurService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DonneurServlet extends HttpServlet {

    private DonneurService service = new DonneurService();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        List<Donneur> donneurs = service.listerTous();
        req.setAttribute("donneurs", donneurs);
        req.setAttribute("groupes", GroupeSanguin.values());
        req.setAttribute("sexes", Sexe.values());
        req.getRequestDispatcher("/listDonneurs.jsp").forward(req, resp);
    }

    // add donor
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        // Simple parsing (no heavy validation - beginner friendly)
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String cin = req.getParameter("cin");
        String tel = req.getParameter("telephone");
        String naissance = req.getParameter("dateNaissance"); // yyyy-MM-dd
        String poidsStr = req.getParameter("poids");
        String sexeStr = req.getParameter("sexe");
        String groupe = req.getParameter("groupeSanguin");
        // check required
        if (nom == null || prenom == null || cin == null) {
            req.setAttribute("error", "Nom/prénom/CIN requis.");
            doGet(req, resp);
            return;
        }

        Donneur d = new Donneur();
        d.setNom(nom);
        d.setPrenom(prenom);
        d.setCin(cin);
        d.setTelephone(tel);
        try {
            if (naissance != null && !naissance.isEmpty()) {
                d.setDateNaissance(LocalDate.parse(naissance));
            }
        } catch (Exception e) { /* ignore for simplicity */ }

        try {
            d.setPoids(Double.parseDouble(poidsStr));
        } catch (Exception e) { d.setPoids(0.0); }

        try { d.setSexe(Sexe.valueOf(sexeStr)); } catch (Exception e) { d.setSexe(Sexe.MASCULIN); }
        try { d.setGroupeSanguin(GroupeSanguin.valueOf(groupe)); } catch (Exception e) { d.setGroupeSanguin(GroupeSanguin.O_NEGATIF); }

        // check checkboxes for pathologies (they come as "on" if checked)
        d.setHepatiteB(req.getParameter("hepatiteB") != null);
        d.setHepatiteC(req.getParameter("hepatiteC") != null);
        d.setVih(req.getParameter("vih") != null);
        d.setDiabete(req.getParameter("diabete") != null);
        d.setGrossesse(req.getParameter("grossesse") != null);
        d.setAllaitement(req.getParameter("allaitement") != null);

        // service handles statut automatic
        service.ajouterDonneur(d);
        resp.sendRedirect(req.getContextPath() + "/donors");
    }
}