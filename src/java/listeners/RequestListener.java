/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.util.Locale;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lookforbooks.Translator;
import lookforbooks.core.utils.XCookies;

/**
 *
 * @author jorgen
 */
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        this.manageLanguage(sre.getServletRequest());
    }
    
    private void manageLanguage(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Translator tr = Translator.getInstance();
        
        XCookies cookies = new XCookies(httpRequest.getCookies());
        
        String lang = cookies.get("lang");
        if (lang != null && !lang.equals(tr.getLocale())) {
            Translator.setLocale(new Locale(lang));
        } 
         
        httpRequest.setAttribute("tr", tr);
    }
}
