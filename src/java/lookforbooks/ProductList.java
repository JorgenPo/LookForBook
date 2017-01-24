/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks;

import buisness.Book;
import buisness.Cart;
import buisness.Invoice;
import buisness.Review;
import buisness.User;
import db.BooksDB;
import db.HibernateUtil;
import db.InvoiceDB;
import db.ReviewsDB;
import db.UsersDB;
import forms.AddBookForm;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author jorgen
 */
public class ProductList extends HttpServlet {
    private boolean isPost = false;
    
    private void indexAction(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        ServletConfig config  = this.getServletConfig();
        HttpSession session = request.getSession();
        Translator tr = (Translator) request.getAttribute("tr");
        
        request.setCharacterEncoding("UTF-8");
        
        BooksDB bdb = new BooksDB();
        
        List<Book> products;
        
        String query;
        
        if((query = request.getParameter("searchQuery")) != null && !query.equals("")) {
            products = bdb.getBooksLike(query);
            request.setAttribute("searchQuery", query);
        } else {
            products = bdb.getBooksList(0, -1);
        }
       
        if (products == null || products.isEmpty()) {
            if (query != null) {
                request.setAttribute("error", tr.translate("No search results by query"));
            } else {
                request.setAttribute("error", tr.translate("No items in database"));
            }
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
                .getRequestDispatcher("/productlist/index.jsp")
                .forward(request, response);
    }
    
    private void addAction(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        ServletConfig config  = this.getServletConfig();
        HttpSession session = request.getSession();
        Translator tr = (Translator) session.getAttribute("tr");
        
        AddBookForm form = new AddBookForm();
        
        if (this.isPost) {
            form.fill(request);
            
            if (form.validate()) {
                Book newBook = form.constructBook();
                if (newBook != null) {
                    BooksDB bdb = new BooksDB();
                    if (bdb.submitBook(newBook)) {
                        response.sendRedirect(context.getAttribute("APP") + "/books");
                    }
                }
            }
        } else {
        
            request.setAttribute("form", form);

            this.getServletContext()
                    .getRequestDispatcher("/productlist/additem.jsp")
                    .forward(request, response);
        }
    }
    
    private void reviewAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsersDB db = new UsersDB();
        User user = db.getUserByEmail(request.getRemoteUser());
        
        request.setCharacterEncoding("UTF-8");
        
        if ( user == null ) {
            response.setStatus(403);
        } 
        
        String text = URLDecoder.decode(request.getParameter("text"), "UTF-8");
        
        if ( text == null ) {
            response.setStatus(400);
        }
        
        Review review = new Review();
        review.setDate(new Date());
        review.setUser(user);
        review.setText(text);
        
        ReviewsDB rdb = new ReviewsDB();
        rdb.addReview(review);
        
        response.setStatus(200);
     }
    
     private void cartAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BooksDB db = new BooksDB();
        
        Cart cart = (Cart) session.getAttribute("cart");
        
        String id;
        Enumeration<String> st = request.getParameterNames();
        if ((id = request.getParameter("add")) != null) {
            cart.addItem(db.getBookById(Integer.parseInt(id)));
            response.setStatus(SC_OK);
            return;
        } else if ((id = request.getParameter("remove")) != null){
            cart.deleteItem(db.getBookById(Integer.parseInt(id)));
            response.setStatus(SC_OK);
            return;
        }
        
        this.getServletContext()
                   .getRequestDispatcher("/cart/cart.jsp")
                   .forward(request, response);
     }
    
    private void orderAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BooksDB bookdb = new BooksDB();
        
        request.setCharacterEncoding("UTF-8");
        
        Cart cart = (Cart) session.getAttribute("cart");
        
        User user = null;
        if ( request.getRemoteUser() != null ) {
            UsersDB userdb = new UsersDB();
            user = userdb.getUserByEmail(request.getRemoteUser());
            
            if ( user != null ) {
                request.getSession().setAttribute("user", user);
            } else {
                response.sendRedirect("/auth/login.jsp");
            }
        }
        
        String forward = "/cart/order.jsp";
        
        if ( request.getParameter("complete") != null ) {
                    
            String delivery = 
                    request.getParameter("address").contains("Магазин") ?
                    "n" : "y";
            
            Invoice invoice = cart.getInvoice();
            invoice.setInvoiceDate(new Date());
            invoice.setUser(user);
            invoice.setAddress(request.getParameter("address"));
            invoice.setDelivery(delivery);
            invoice.setDeliveryCost((int) (0.1f * cart.getTotalPrice()));
            invoice.setTotalAmount(cart.getTotalPrice());
            invoice.setIsProcessed("n");
            InvoiceDB idb = new InvoiceDB();
            
            idb.addInvoice(invoice);
            
            session.setAttribute("cart", new Cart());
            forward = "books";
        }
        
        response.setCharacterEncoding("UTF-8");
                
        this.getServletContext()
                   .getRequestDispatcher("/cart/order.jsp")
                   .forward(request, response);
    }
    
    private void doRoute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            String action = request.getRequestURI();
            
            if (action.endsWith("/books/add")) {
                this.addAction(request, response);
            } else if (action.endsWith("/cart")) {
                this.cartAction(request, response);
            } else if (action.endsWith("/order")) {
                this.orderAction(request, response);
            } else if (action.endsWith("/review")) {
                this.reviewAction(request, response);
            } else {
                this.indexAction(request, response);
            }
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        ServletConfig config  = this.getServletConfig();
        HttpSession session = request.getSession();
        
        this.doRoute(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       this.isPost = false;
       this.doRequest(request, response);
    }

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
        return "List of items - main page";
    }
}
