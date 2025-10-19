package com.donnedesang.controller;

import com.donnedesang.model.Donneur;
import com.donnedesang.model.GroupeSanguin;
import com.donnedesang.model.Sexe;
import com.donnedesang.service.DonneurService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DonneurServlet extends HttpServlet {

    private final DonneurService service = new DonneurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Donneur> donneurs = service.listerTous();
        req.setAttribute("donneurs", donneurs);
        req.setAttribute("groupes", GroupeSanguin.values());
        req.setAttribute("sexes", Sexe.values());
        req.getRequestDispatcher("/view/listDonneurs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String cin = req.getParameter("cin");
        String tel = req.getParameter("telephone");
        String naissance = req.getParameter("dateNaissance");
        String poidsStr = req.getParameter("poids");
        String sexeStr = req.getParameter("sexe");
        String groupe = req.getParameter("groupeSanguin");

        if (nom == null || prenom == null || cin == null) {
            req.setAttribute("error", "Nom, prénom et CIN sont requis !");
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            d.setPoids(Double.parseDouble(poidsStr));
        } catch (Exception e) {
            d.setPoids(0.0);
        }

        try {
            d.setSexe(Sexe.valueOf(sexeStr));
        } catch (Exception e) {
            d.setSexe(Sexe.MASCULIN);
        }

        try {
            d.setGroupeSanguin(GroupeSanguin.valueOf(groupe));
        } catch (Exception e) {
            d.setGroupeSanguin(GroupeSanguin.O_NEGATIF);
        }

        d.setHepatiteB(req.getParameter("hepatiteB") != null);
        d.setHepatiteC(req.getParameter("hepatiteC") != null);
        d.setVih(req.getParameter("vih") != null);
        d.setDiabete(req.getParameter("diabete") != null);
        d.setGrossesse(req.getParameter("grossesse") != null);
        d.setAllaitement(req.getParameter("allaitement") != null);

        service.ajouterDonneur(d);

        // Redirige vers la liste
        resp.sendRedirect(req.getContextPath() + "/donors");
    }
}