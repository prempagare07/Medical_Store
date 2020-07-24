/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.servlets;

import com.AppStore.data.MysubscriptionDao;
import com.AppStore.domain.mysubscription;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SAMRUDDHI
 */
@WebServlet(name = "adminSubscriptionControl", urlPatterns = {"/SubscriptionControl"})
public class adminSubscriptionControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MysubscriptionDao mysub = null;
    public adminSubscriptionControl()
    {  
       
       mysub = new MysubscriptionDao();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
		String action = (String)request.getParameter("func1");
                
                if(action != null){
                    try {

                        switch (action) {
                        case "accept":
                            acceptRequest(request, response);
                            break;
                        case "reject":
                            rejectRequest(request, response);
                            break;
                        case "pending":
                            printPendingRequests(request,response);
                            break;
                        default:
                            listall(request, response);
                            break;
                        }



                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        // TODO Auto-generated catch block

                    }
                }
                else
                {
                                     try ( PrintWriter out = response.getWriter()) {
        
            out.println("<p>action<p>");}  
                }
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
       

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         String name = request.getParameter("uname");
         MysubscriptionDao mysub = new MysubscriptionDao();
         mysub.setStatus(id,name,"subcribed!");
         response.sendRedirect("SubscriptionControl?func1=list");
    }

    private void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("uname");
        MysubscriptionDao mysub = new MysubscriptionDao();
        mysub.setStatus(id,name,"SORRY!REQUEST-REJECTED.");
        response.sendRedirect("SubscriptionControl?func1=list");
    }

    private void printPendingRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        List<mysubscription> appl = new ArrayList<>();
        appl = mysub.getAllPendingSubscriptions("pending...");
        request.setAttribute("appl", appl);
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/subscriptionlist.jsp");
        dispatch.forward(request, response);
    }

    private void listall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List<mysubscription> appl = new ArrayList<>();
        appl = mysub.getAllmysubscriptions("subscribed!");
        response.setContentType("text/html;charset=UTF-8");
	if(appl!=null){
            request.setAttribute("appl", appl);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/subscriptionlist.jsp");
            dispatch.forward(request, response);
        }
        else{
                response.sendRedirect("SubscriptionControl?func1=pending");
        } 
    }
}
