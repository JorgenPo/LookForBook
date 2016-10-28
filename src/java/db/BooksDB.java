/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import buisness.Books;

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
        List<Books> books = null;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("from Books");
            q.setFirstResult(from);
            q.setMaxResults(to - from);
            
            books = (List<Books>) q.list();
            
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
        List<Books> list = null;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            
            Query q = session.createQuery("FROM Books B WHERE"
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
    
    public Books getBookById(Integer id) {
        Books book = null;
        
        Transaction t = null;
        try {
            t = session.beginTransaction();
            
            Query q = session.createQuery("FROM Books B WHERE B.id = :id");
            q.setInteger("id", id);
            
            book = (Books) q.uniqueResult();
            
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
            Query q = session.createQuery("SELECT COUNT(*) FROM Books");
            
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
