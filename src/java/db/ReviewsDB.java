/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import buisness.Review;
import buisness.User;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author jorgen
 */
public class ReviewsDB {
    Session session = null;
    
    public ReviewsDB() {
        this.session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
    }
    
    public void addReview(Review review) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            session.save(review);
            
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
    
    public ArrayList<Review> getUserReviews(User user) {
        ArrayList<Review> list = new ArrayList<>();
        Session session = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            
            Query q = session.createQuery("FROM Review R"
                    + " WHERE R.user = :user");
            q.setParameter("user", user.getUserId());
            
            list = (ArrayList<Review>) q.list();
            
            
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
