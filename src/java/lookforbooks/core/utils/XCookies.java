/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;

/**
 *
 * @author jorgen
 */
public class XCookies {
    Map<String, String> cookies;
    
    public XCookies(Cookie[] cookies) {
        this.cookies = new HashMap<>();
        
        if (cookies == null) {
            return;
        }
        
        for (int i = 0; i < cookies.length; ++i) {
            this.cookies.put(cookies[i].getName(), cookies[i].getValue());
        }
    }
    
    public String get(String name) {
        return cookies.get(name);
    }
    
    public XCookies set(String name, String value) {
        cookies.put(name, value);
        return this;
    }
}
