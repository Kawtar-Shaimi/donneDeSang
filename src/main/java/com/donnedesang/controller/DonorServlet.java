package com.donnedesang.controller;

import com.donnedesang.model.Donor;
import com.donnedesang.service.DonorService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DonorServlet extends HttpServlet {

    private DonorService donorService = new DonorService();

    // Afficher la liste des donneurs
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer la liste des donneurs depuis le service
        List<Donor> donors = donorService.getAllDonors();
        request.setAttribute("donors", donors);

        // Redirection vers la page JSP d'affichage
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/donors-list.jsp");
        dispatcher.forward(request, response);
    }

    // Ajouter un nouveau donneur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Donor donor = new Donor();
        donor.setFirstName(request.getParameter("firstName"));
        donor.setLastName(request.getParameter("lastName"));
        donor.setAge(Integer.parseInt(request.getParameter("age")));
        donor.setCin(request.getParameter("cin"));
        donor.setPhone(request.getParameter("phone"));
        donor.setWeight(Double.parseDouble(request.getParameter("weight")));
        donor.setBloodGroup(request.getParameter("bloodGroup"));
        donor.setHasContraindications(Boolean.parseBoolean(request.getParameter("hasContraindications")));

        // Déterminer le statut automatiquement
        donorService.updateDonorStatus(donor);

        // Après ajout, rediriger vers la liste
        response.sendRedirect("donors");
    }
}
