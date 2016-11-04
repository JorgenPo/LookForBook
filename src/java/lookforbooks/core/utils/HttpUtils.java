/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jorgen
 */
public class HttpUtils {
    public static HttpServletRequest clearRequest(HttpServletRequest request) {
        for (Enumeration<String> v = request.getAttributeNames(); v.hasMoreElements();) {
            request.removeAttribute(v.nextElement());
        }
        return request;
    }
    
    public static Map<String, String> parseRequestParams(HttpServletRequest request) {
        TreeMap<String, String> params = new TreeMap<>();
        
        String name;
        for (Enumeration<String> v = request.getParameterNames(); v.hasMoreElements();) {
            name = v.nextElement();
            params.put(name, request.getParameter(name));
        }
        
        return params;
    }
}
