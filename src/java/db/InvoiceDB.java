/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import buisness.Book;
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
}
