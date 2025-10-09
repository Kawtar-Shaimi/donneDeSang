package com.donnedesang.controller;

import com.donnedesang.model.Receiver;
import com.donnedesang.dao.ReceiverDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ReceiverServlet extends HttpServlet {

    private ReceiverDAO receiverDAO = new ReceiverDAO();

    // GET → afficher la liste des receveurs
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Receiver> receivers = receiverDAO.findAll();
        request.setAttribute("receivers", receivers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("receivers-list.jsp");
        dispatcher.forward(request, response);
    }

    // POST → ajouter un nouveau receveur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Receiver receiver = new Receiver();
        receiver.setFirstName(request.getParameter("firstName"));
        receiver.setLastName(request.getParameter("lastName"));
        receiver.setEmail(request.getParameter("email"));
        receiver.setPhone(request.getParameter("phone"));
        receiver.setCin(request.getParameter("cin"));
        receiver.setBirthday(request.getParameter("birthday"));
        receiver.setSexe(request.getParameter("sexe"));
        receiver.setBloodGroup(request.getParameter("bloodGroup"));
        receiver.setNeedType(request.getParameter("needType"));

        receiver.setSatisfied(false);

        receiverDAO.create(receiver);

        response.sendRedirect("receivers");
    }
}
