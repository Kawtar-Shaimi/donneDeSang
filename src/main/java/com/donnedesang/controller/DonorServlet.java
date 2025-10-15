package com.donnedesang.controller;

import com.donnedesang.model.Donor;
import com.donnedesang.service.DonorService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DonorServlet extends HttpServlet {

    private DonorService donorService = new DonorService();

    // 🔹 Afficher la liste des donneurs ou le formulaire de modification
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Donor donor = donorService.getDonorById(id);
            request.setAttribute("donor", donor);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edit-donor.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Donor> donors = donorService.getAllDonors();
            request.setAttribute("donors", donors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/donors-list.jsp");
            dispatcher.forward(request, response);
        }
    }

    // 🔹 Ajouter ou modifier un donneur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            // Modification d’un donneur existant
            Long id = Long.parseLong(request.getParameter("id"));
            Donor donor = donorService.getDonorById(id);

            donor.setFirstName(request.getParameter("firstName"));
            donor.setLastName(request.getParameter("lastName"));
            donor.setCin(request.getParameter("cin"));
            donor.setPhone(request.getParameter("phone"));
            donor.setBloodGroup(request.getParameter("bloodGroup"));

            String ageParam = request.getParameter("age");
            String weightParam = request.getParameter("weight");

            if (ageParam != null && !ageParam.isEmpty()) {
                donor.setAge(Integer.parseInt(ageParam));
            }
            if (weightParam != null && !weightParam.isEmpty()) {
                donor.setWeight(Double.parseDouble(weightParam));
            }

            donor.setHasContraindications(
                    "yes".equalsIgnoreCase(request.getParameter("hasContraindications"))
            );

            donorService.updateDonor(donor);
            response.sendRedirect("donors");

        } else {
            // Ajout d’un nouveau donneur
            Donor donor = new Donor();
            donor.setFirstName(request.getParameter("firstName"));
            donor.setLastName(request.getParameter("lastName"));
            donor.setCin(request.getParameter("cin"));
            donor.setPhone(request.getParameter("phone"));
            donor.setBloodGroup(request.getParameter("bloodGroup"));

            String ageParam = request.getParameter("age");
            String weightParam = request.getParameter("weight");

            if (ageParam != null && !ageParam.isEmpty()) {
                donor.setAge(Integer.parseInt(ageParam));
            }
            if (weightParam != null && !weightParam.isEmpty()) {
                donor.setWeight(Double.parseDouble(weightParam));
            }

            donor.setHasContraindications(
                    "yes".equalsIgnoreCase(request.getParameter("hasContraindications"))
            );

            donorService.updateDonor(donor); // ← corrigé ici

            response.sendRedirect("donors");
        }
    }
}
