package com.donnedesang.controller;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;
import com.donnedesang.service.MatchService;
import com.donnedesang.dao.DonorDAO;
import com.donnedesang.dao.ReceiverDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MatchServlet extends HttpServlet {

    private MatchService matchService;
    private DonorDAO donorDAO;
    private ReceiverDAO receiverDAO;

    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
        donorDAO = new DonorDAO();
        receiverDAO = new ReceiverDAO();
    }

    // Afficher la page de matching
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Receveurs non satisfaits triés par priorité
        List<Receiver> receivers = receiverDAO.findAll(); // Tu peux filtrer les SATISFIED si tu veux

        // Donneurs disponibles
        List<Donor> donors = donorDAO.findAll(); // tu peux filtrer DISPONIBLE si tu veux

        // Attribuer les listes à la requête pour JSP
        request.setAttribute("receivers", receivers);
        request.setAttribute("donors", donors);

        request.getRequestDispatcher("match.jsp").forward(request, response);
    }

    // Associer un donneur à un receveur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String donorParam = request.getParameter("donorId");
        String receiverParam = request.getParameter("receiverId");

        if (donorParam == null || receiverParam == null) {
            response.sendRedirect(request.getContextPath() + "/match");
            return;
        }

        try {
            Long donorId = Long.parseLong(donorParam);
            Long receiverId = Long.parseLong(receiverParam);

            Donor donor = donorDAO.findById(donorId);
            Receiver receiver = receiverDAO.findById(receiverId);

            if (donor != null && receiver != null) {
                boolean success = matchService.assignDonorToReceiver(donor, receiver);

                if (success) {
                    request.getSession().setAttribute("message", "✅ Donneur associé avec succès !");
                } else {
                    request.getSession().setAttribute("message", "⚠️ Impossible d'associer ce donneur.");
                }
            }

            response.sendRedirect(request.getContextPath() + "/match?receiverId=" + receiverId);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/match");
        }
    }
}
