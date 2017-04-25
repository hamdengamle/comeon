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
 * Created by Julius on 29-03-2017.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Calculations BL = new Calculations();
            String name = request.getParameter("uName");
            String pass = request.getParameter("pWord");

            String admin = name;
            HttpSession session = request.getSession();
            //https://www.owasp.org/index.php/Session_fixation
            //Skal muligvis bruges til at stoppe session attacks
            //request.changeSessionId();

            int level = BL.validate(name, pass);
            switch(level){
                case 1:
                    request.changeSessionId();
                    session.setAttribute("admin", admin);
                    response.sendRedirect("adminPage.jsp");
                    break;
                case 2:
                    request.changeSessionId();
                    session.setAttribute("username", name);
                    response.sendRedirect("welcome.jsp");
                    break;

                default: response.sendRedirect("index.jsp");
            }
    }
}
