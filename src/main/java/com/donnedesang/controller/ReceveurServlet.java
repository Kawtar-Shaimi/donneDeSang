package com.donnedesang.controller;

import com.donnedesang.model.Receveur;
import com.donnedesang.model.GroupeSanguin;
import com.donnedesang.model.Sexe;
import com.donnedesang.service.ReceveurService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReceveurServlet extends HttpServlet {

    private final ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Receveur> receveurs = receveurService.listerTousParPriorite();
        req.setAttribute("receveurs", receveurs);
        req.setAttribute("groupes", GroupeSanguin.values());
        req.setAttribute("sexes", Sexe.values());
        req.getRequestDispatcher("/view/listReceveurs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Receveur r = new Receveur();
        r.setNom(req.getParameter("nom"));
        r.setPrenom(req.getParameter("prenom"));
        r.setCin(req.getParameter("cin"));
        r.setTelephone(req.getParameter("telephone"));

        try {
            String date = req.getParameter("dateNaissance");
            if (date != null && !date.isEmpty()) {
                r.setDateNaissance(LocalDate.parse(date));
            }
        } catch (Exception ignored) {}

        try {
            r.setSexe(Sexe.valueOf(req.getParameter("sexe")));
        } catch (Exception e) {
            r.setSexe(Sexe.MASCULIN);
        }

        try {
            r.setGroupeSanguin(GroupeSanguin.valueOf(req.getParameter("groupeSanguin")));
        } catch (Exception e) {
            r.setGroupeSanguin(GroupeSanguin.O_NEGATIF);
        }

        String situation = req.getParameter("situation");
        switch (situation) {
            case "CRITIQUE" -> r.setBesoinPoches(4);
            case "URGENT" -> r.setBesoinPoches(3);
            default -> r.setBesoinPoches(1);
        }

        r.setPocheRecues(0);
        r.setSatisfait(false);

        receveurService.ajouterReceveur(r);
        resp.sendRedirect(req.getContextPath() + "/receveurs");
    }
}