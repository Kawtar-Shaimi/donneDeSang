package com.donnedesang.controller;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;
import com.donnedesang.dao.DonorDAO;
import com.donnedesang.dao.ReceiverDAO;
import com.donnedesang.service.DonorService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MatchServlet extends HttpServlet {

    private DonorService donorService = new DonorService();
    private DonorDAO donorDAO = new DonorDAO();
    private ReceiverDAO receiverDAO = new ReceiverDAO();

    // 👉 Associer un donneur à un receveur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long donorId = Long.parseLong(request.getParameter("donorId"));
        Long receiverId = Long.parseLong(request.getParameter("receiverId"));

        Donor donor = donorDAO.findById(donorId);
        Receiver receiver = receiverDAO.findById(receiverId);

        donorService.assignDonorToReceiver(donor, receiver);

        response.sendRedirect("receivers");
    }

    // 👉 Afficher les donneurs compatibles pour un receveur donné
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long receiverId = Long.parseLong(request.getParameter("receiverId"));
        Receiver receiver = receiverDAO.findById(receiverId);

        List<Donor> donors = donorDAO.findAll();
        List<Donor> compatibleDonors = donorService.findCompatibleDonors(donors, receiver);

        request.setAttribute("receiver", receiver);
        request.setAttribute("compatibleDonors", compatibleDonors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("match.jsp");
        dispatcher.forward(request, response);
    }
}
