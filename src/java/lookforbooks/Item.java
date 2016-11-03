/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks;

import buisness.Book;
import db.BooksDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorgen
 */
public class Item extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        ServletConfig config = this.getServletConfig();
        HttpSession session = request.getSession();
        
        BooksDB bdb = new BooksDB();

        String id = request.getParameter("id");
        Book book = bdb.getBookById(Integer.parseInt(id));

        if (book == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("item", book);
        request.setAttribute("defaultPage", config.getInitParameter("defaultPage"));
        
        // Set up Translator if need
        Translator tr = (Translator) session.getAttribute("tr");
        String lang;
        if ((lang = (String) request.getParameter("lang")) != null) {
            tr = new Translator(new Locale(lang));
            session.setAttribute("tr", tr);
        }
        
        if (tr == null) {
            tr = new Translator(request.getLocale());
            session.setAttribute("tr", tr);
        }
       
        this.getServletContext()
                .getRequestDispatcher("/item/item.jsp")
                .forward(request, response);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
