/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Web application lifecycle listener.
 *
 * @author jorgen
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static final String HOST = "http://localhost:8080";
    private static final String MODULES = "LookForBook-war/WEB-INF/modules";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("HOST", HOST);
        context.setAttribute("MODULES", MODULES);
        
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        context.setAttribute("YEAR", year.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
