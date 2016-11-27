/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import buisness.Cart;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author jorgen
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession s = se.getSession();
        
        if (s.getAttribute("cart") == null) {
            s.setAttribute("cart", new Cart());
        } 
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
    
}
