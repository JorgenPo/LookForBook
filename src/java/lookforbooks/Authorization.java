/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks;

import buisness.User;
import db.UsersDB;
import forms.LoginForm;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import javax.servlet.http.HttpSession;
import lookforbooks.core.Form;

/**
 *
 * @author jorgen
 */
public class Authorization extends HttpServlet {
    protected boolean isPost;
    
    private void profileAction (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        User user;
        if ( request.getRemoteUser() != null ) {
            UsersDB db = new UsersDB();
            user = db.getUserByEmail(request.getRemoteUser());
            
            if ( user != null ) {
                request.getSession().setAttribute("user", user);
            }
        }
        
        request.setAttribute("defaultPage", this .getInitParameter("defaultPage"));
        
        this.getServletContext()
                   .getRequestDispatcher("/user/profile.jsp")
                   .forward(request, response);
    }
    
    private void loginAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        LoginForm loginForm = new LoginForm();  
        
        if (this.isPost) {
            try {
                loginForm.fill(request);
                
                if (loginForm.validate()) {
                    User user = loginForm.constructUser();
                    
                    if (user == null) {
                        request.setAttribute("error", "No such user!");
                    } else {
                        request.getSession().setAttribute("user", user);
                        
                        response.sendRedirect(request.getContextPath() + "/books");
                        return;
                    }
                }
                
            } catch (Exception e) {
                this.log(e.getLocalizedMessage());
            }
        }
        
        request.setAttribute("form", loginForm);
        
        this.getServletContext()
                   .getRequestDispatcher("/auth/login.jsp")
                   .forward(request, response);
    }
     
     private void logoutAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.getSession().invalidate();
        
        this.getServletContext()
                   .getRequestDispatcher("/books")
                   .forward(request, response);
    }
    
    private void doRoute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            String action = request.getRequestURI();
            
            if (action.endsWith("/login")) {
                loginAction(request, response);
            } else if (action.endsWith("/logout")) {
                logoutAction(request, response);
            } else if (action.endsWith("/profile")) {
                profileAction(request, response);
            } else {
                response.setStatus(SC_NOT_FOUND);
            }
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        HttpSession session = request.getSession();
        
        this.doRoute(request, response);
    }
   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.isPost = false;
        this.doRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.isPost = true;
        this.doRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
