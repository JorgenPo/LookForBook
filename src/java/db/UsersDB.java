/*
 * Copyright (C) 2016 root
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package db;

import buisness.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class UsersDB {
    private final Class beanClass = User.class;
    
    public UsersDB() {
    }
    
    public User getUserByEmail(String email) { 
        User user = null;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            user = (User) session.createCriteria(beanClass)
                    .add( Restrictions.like("email", email) )
                    .uniqueResult();
            
            session.getTransaction().commit();
        } catch(Exception e) {
            if (session != null &&
                session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return user;
    }
}
