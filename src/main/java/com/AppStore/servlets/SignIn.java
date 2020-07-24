package com.AppStore.servlets;
import com.AppStore.data.SignInDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        SignInDao dao = new SignInDao();


        if(name.isEmpty() || pass.isEmpty() || pass.isEmpty() || tel.isEmpty()) {
            String msg = "Fields can't be empty";
            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("SignIn.jsp");
        }
        else if(tel.length()!=10){
            String msg = "Invalid telephone no";
            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("SignIn.jsp");
        }
        else {
            try {
                if(dao.checkDetails(name,email, tel, pass)) {
                    String msg = "User already exists";
                    request.getSession().setAttribute("msg", msg);
                    response.sendRedirect("SignIn.jsp");
                }
                else {
                    response.sendRedirect("login.jsp");
                }
            } catch (SQLException e) {
            }

        }
    }

}
