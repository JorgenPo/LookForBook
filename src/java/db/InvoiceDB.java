/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import buisness.Book;
import buisness.Invoice;
import buisness.User;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author jorgen
 */
public class InvoiceDB {
    Session session = null;
    
    public InvoiceDB() {
        this.session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
    }
    
    public void addInvoice(Invoice invoice) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            session.save(invoice);
            
            session.getTransaction().commit();
        } catch(HibernateException e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public ArrayList<Invoice> getUserInvoices(User user) {
        ArrayList<Invoice> list = new ArrayList<>();
        Session session = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            Query q = session.createQuery("FROM Invoice I"
                    + " WHERE I.user = :user");
            q.setParameter("user", user.getUserId());
            
            list = (ArrayList<Invoice>) q.list();
            
            
            session.getTransaction().commit();
        } catch(HibernateException e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return list;
    }
    
    public ArrayList<Book> getInvoiceBooks(Invoice invoice) {
        ArrayList<Book> list = new ArrayList<>();
        Session session = null;
        
        ArrayList<Integer> books = new ArrayList<>();
        invoice.getLineItems().forEach(item -> {
            books.add(item.getBookId());
        });
        
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            Query q = session.createQuery("FROM Book B"
                    + " WHERE B.id IN :books");
            q.setParameterList("books", books);
            
            list = (ArrayList<Book>) q.list();
            session.getTransaction().commit();
        } catch(HibernateException e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return list;
    }
}
