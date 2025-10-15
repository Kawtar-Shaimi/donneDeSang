package com.donnedesang.controller;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;
import com.donnedesang.service.DonorService;
import com.donnedesang.service.MatchService;
import com.donnedesang.service.ReceiverService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private DonorService donorService = new DonorService();
    private ReceiverService receiverService = new ReceiverService();
    private MatchService matchService = new MatchService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les IDs depuis les paramètres
        String donorIdParam = request.getParameter("donorId");
        String receiverIdParam = request.getParameter("receiverId");

        if (donorIdParam != null && receiverIdParam != null) {
            Long donorId = Long.parseLong(donorIdParam);
            Long receiverId = Long.parseLong(receiverIdParam);

            // Obtenir les objets directement depuis les services
            Donor donor = donorService.getDonorById(donorId);
            Receiver receiver = receiverService.getReceiverById(receiverId);

            if (donor != null && receiver != null) {
                // Faire le match
                matchService.matchDonorToReceiver(donor, receiver);

                // Ajouter le donneur à la liste du receveur
                receiver.addDonor(donor);

                // Mettre à jour le receveur et le donneur
                receiverService.updateReceiver(receiver);
                donorService.updateDonor(donor);

                request.setAttribute("message", "Le donateur a été assigné avec succès au receveur !");
            } else {
                request.setAttribute("message", "Donneur ou receveur introuvable.");
            }
        }

        // Rediriger ou afficher la page de match
        request.getRequestDispatcher("/match.jsp").forward(request, response);
    }
}