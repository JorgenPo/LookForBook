/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import buisness.Book;
import java.util.ArrayList;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jorgen
 */
public class BooksDB {
    private final Class beanClass = Book.class;
    
    public BooksDB() {
    }
    
    public List getBooksList(int from, int to) {
        List<Book> books = new ArrayList<>();
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            session.beginTransaction();
            
            books = session
                    .createCriteria(beanClass)
                    .setFirstResult(from)
                    .setMaxResults(to - from)
                    .list();
            
            session.getTransaction().commit();
        } catch(Exception e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return books;
    }
    
    public List getBooksLike(String pattern) {
        List<Book> list = null;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            Query q = session.createQuery("FROM Book B WHERE"
                    + " B.title LIKE :searchPattern OR"
                    + " B.author LIKE :searchPattern");
            
            q.setString("searchPattern", "%" + pattern + "%");
            
            list = q.list();
            
            session.getTransaction().commit();
        } catch(Exception e) {
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
    
    public Book getBookById(Integer id) {
        Book book = null;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            book = (Book) session.get(beanClass, id);
            
            session.getTransaction().commit();
        } catch(Exception e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return book;
    }
    
    public int getBooksCount() {
        int count = 0;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            Query q = session.createQuery("SELECT COUNT(*) FROM Book");
            
            count = (Integer) q.uniqueResult();
            session.getTransaction().commit();
        } catch(Exception e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return count;
    }
    
    public boolean submitBook(Book book) {
        boolean success = true;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            session.save(book);
            
            session.getTransaction().commit();
        } catch(Exception e) {
            if (session != null & 
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return success;
    }
}
