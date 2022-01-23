package com.mirka.ms.controller;

import com.mirka.ms.controller.command.Command;
import com.mirka.ms.controller.command.CommandName;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionRequestContext sessionRequestContext = new SessionRequestContext(request);
        CommandName commandName = CommandName.valueOf(request.getParameter("command").toUpperCase());
        Command command = commandName.getCommand();
        String page = command.execute(sessionRequestContext);
        sessionRequestContext.insertAttributes(request);
        response.sendRedirect(request.getContextPath() + page);
    }
}