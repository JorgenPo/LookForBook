/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks;

import buisness.Books;
import db.BooksDB;
import java.io.IOException;
import java.util.List;
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
public class ProductList extends HttpServlet {
    
    private BooksDB bdb;
    

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
        ServletContext context = this.getServletContext();
        ServletConfig config  = this.getServletConfig();
        HttpSession session = request.getSession();
        
        BooksDB bdb = new BooksDB();
        
        List<Books> products = null;
        String query = null;
        
        if((query = request.getParameter("searchQuery")) != null && !query.equals("")) {
            products = bdb.getBooksLike(query);
            request.setAttribute("searchQuery", query);
        } else {
            products = bdb.getBooksList(0, -1);
        }
        
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
       
        if (products.isEmpty()) {
            request.setAttribute("error", tr.translate("No search results by query"));
        }
        
        request.setAttribute("products", products);
        
        // Set initial params as attrs
        String productRowLength = config.getInitParameter("productRowLength");
        String itemWidth = config.getInitParameter("productImgWidth");
        String itemHeight = config.getInitParameter("productImgHeight");
        
        request.setAttribute("productRowLength", productRowLength);
        request.setAttribute("productImgWidth", itemWidth);
        request.setAttribute("productImgHeight", itemHeight);
        
        
        this.getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request, response);
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
        this.doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "List of items - main page";
    }
}
