package com.AppStore.servlets;

import java.io.IOException;
import java.util.List;

import com.AppStore.domain.AppCategory;
import com.AppStore.domain.Application;
import com.AppStore.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AppCrud appcrud = null;


    public AdminController() {
        super();
        String jdbcURL = "jdbc:mysql://localhost:3306/awp";
        String jdbcUsername = "root";
        String jdbcPassword = Utils.SQL_PASSWORD;

        appcrud = new AppCrud(jdbcURL, jdbcUsername, jdbcPassword);

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if(session.getAttribute("isadmin")!=null){
            isAdmin = (boolean)session.getAttribute("isadmin");
        }
        String action = (String) request.getParameter("func");
        if (action == null) {
            if(isAdmin){
                try {
                    listApp(request,response);
                } catch (Exception ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                response.sendRedirect("index.html");   
            }
            
        } else {
            try {

                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertApp(request, response);
                        break;
                    case "delete":
                        deleteApp(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateApp(request, response);
                        break;
                    case "list":
                        listApp(request, response);
                        break;
                    default:
                        if(isAdmin){
                            listApp(request, response);
                            break;
                        }
                        else{
                            response.sendRedirect("index.html");
                            break;
                        }
                        
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    private void listApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Application> applist = appcrud.ListAllApps();

        request.setAttribute("applist", applist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AppList.jsp");
        dispatcher.forward(request, response);
    }

    private void insertApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        int num_download = Integer.parseInt(request.getParameter("downloads"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String logo = request.getParameter("logo");
        double version = Double.parseDouble(request.getParameter("version"));

        Application new_app = new Application(id, name, desc, AppCategory.valueOf(category), num_download, rating, logo, version);
        appcrud.insertApp(new_app);
        response.sendRedirect("admin?func=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddApp.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Application existing_app = appcrud.getApp(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddApp.jsp");
        request.setAttribute("app", existing_app);
        dispatcher.forward(request, response);
    }

    private void updateApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String category = request.getParameter("category");
        int num_download = Integer.parseInt(request.getParameter("downloads"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String logo = request.getParameter("logo");
        double version = Double.parseDouble(request.getParameter("version"));

        Application new_app = new Application(id, name, desc, AppCategory.valueOf(category), num_download, rating, logo, version);
        appcrud.updateApp(new_app);
        response.sendRedirect("admin?func=list");
    }

    private void deleteApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        appcrud.deleteApp(id);
        response.sendRedirect("admin?func=list");
    }


}
