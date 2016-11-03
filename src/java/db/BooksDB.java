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
    Session session = null;
    
    public BooksDB() {
        this.session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
    }
    
    public List getBooksList(int from, int to) {
        List<Book> books = new ArrayList<>();
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("from Book");
            q.setFirstResult(from);
            
            if (to != -1) {
                q.setMaxResults(to - from);
            }
            
            books = (List<Book>) q.list();
            
            t.commit();
        } catch(Exception e) {
            e.printStackTrace();
            if (t != null) {
                t.rollback();
            }
        } 
        
        return books;
    }
    
    public List getBooksLike(String pattern) {
        List<Book> list = null;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            
            Query q = session.createQuery("FROM Book B WHERE"
                    + " B.title LIKE :searchPattern OR"
                    + " B.author LIKE :searchPattern");
            
            q.setString("searchPattern", "%" + pattern + "%");
            
            list = q.list();
            
            t.commit();
        } catch(Exception e) {
            if (t != null) {
                t.rollback();
            }
        }
        
        return list;
    }
    
    public Book getBookById(Integer id) {
        Book book = null;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            
            Query q = session.createQuery("FROM Book B WHERE B.id = :id");
            q.setInteger("id", id);
            
            book = (Book) q.uniqueResult();
            
            t.commit();
        } catch(Exception e) {
            if (t != null) {
                t.rollback();
            }
        }
        
        return book;
    }
    
    public int getBooksCount() {
        int count = 0;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("SELECT COUNT(*) FROM Book");
            
            count = (Integer) q.uniqueResult();
            t.commit();
        } catch(Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        
        return count;
    }
    
}
