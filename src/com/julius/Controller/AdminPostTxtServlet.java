package com.julius.Controller;

import com.julius.BusinessLogic.Calculations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Julius on 30-03-2017.
 */
@WebServlet(name = "AdminPostTxtServlet")
public class AdminPostTxtServlet extends HttpServlet {
    Calculations BL = new Calculations();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String message = request.getParameter("text");
            HttpSession session = request.getSession();
            String name = session.getAttribute("admin").toString();
            BL.message(name, message);
            response.sendRedirect("/adminPage.jsp");
    }
}
