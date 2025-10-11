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

            // Vérifie que les champs numériques sont bien fournis
            String ageParam = request.getParameter("age");
            String weightParam = request.getParameter("weight");

            if (ageParam != null && !ageParam.isEmpty()) {
                donor.setAge(Integer.parseInt(ageParam));
            }
            if (weightParam != null && !weightParam.isEmpty()) {
                donor.setWeight(Double.parseDouble(weightParam));
            }

            donorService.updateDonor(donor);
            response.sendRedirect("donors");

        } else {
            // Ajout d’un nouveau donneur — avec vérification
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String cin = request.getParameter("cin");
            String phone = request.getParameter("phone");
            String bloodGroup = request.getParameter("bloodGroup");
            String ageParam = request.getParameter("age");
            String weightParam = request.getParameter("weight");

            // Vérifie que le formulaire n’est pas vide
            if (firstName != null && !firstName.isEmpty()
                    && lastName != null && !lastName.isEmpty()
                    && cin != null && !cin.isEmpty()
                    && phone != null && !phone.isEmpty()
                    && bloodGroup != null && !bloodGroup.isEmpty()) {

                Donor donor = new Donor();
                donor.setFirstName(firstName);
                donor.setLastName(lastName);
                donor.setCin(cin);
                donor.setPhone(phone);
                donor.setBloodGroup(bloodGroup);

                if (ageParam != null && !ageParam.isEmpty()) {
                    donor.setAge(Integer.parseInt(ageParam));
                }
                if (weightParam != null && !weightParam.isEmpty()) {
                    donor.setWeight(Double.parseDouble(weightParam));
                }

                donor.setHasContraindications(
                        "yes".equalsIgnoreCase(request.getParameter("hasContraindications"))
                );

                donorService.updateDonorStatus(donor);
            }

            // Redirection dans tous les cas
            response.sendRedirect("donors");
        }
    }
}
