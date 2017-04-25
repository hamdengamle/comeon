package com.julius.Controller;

import com.julius.BusinessLogic.Calculations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julius on 16-04-2017.
 */
@WebServlet(name = "NewUser")
public class NewUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Calculations BL = new Calculations();
        String name = request.getParameter("uName");
        String pass = request.getParameter("pWord");
        BL.createUser(name, pass);
        response.sendRedirect("/index.jsp");
    }
}
