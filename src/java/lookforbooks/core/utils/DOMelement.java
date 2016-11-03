/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jorgen
 */
public class DOMelement {
    protected String tagName;
    protected Map<String, String> attributes;
    protected String innerHtml;
    protected String outerHtml;
    protected boolean hasBody;
    
    private boolean needUpdate;
    
    public DOMelement(String tagName) {
        this.tagName = tagName;
        this.attributes = new TreeMap<>();
        this.innerHtml = "";
        this.outerHtml = "";
        
        // There are tags with no body
        this.hasBody = tagName.equals("input") |
                       tagName.equals("link");
        this.hasBody = !this.hasBody;
        
        this.needUpdate = true;
    }
    
    public DOMelement set(String name, String value) {
        if (this.attributes.containsKey(name) && 
           !this.attributes.get(name).equals(value)) {
            this.needUpdate = true;
        }
        
        this.attributes.put(name, value);
        return this;
    }
    
    public DOMelement setInnerHtml(String innerHtml) {
        if (!this.innerHtml.equals(innerHtml)) {
            this.needUpdate = true;
        }
        
        this.innerHtml = innerHtml;
        return this;
    }
    
    public String getInnerHtml() {
        return this.innerHtml;
    }
    
    public String getHtml() {
        if (this.outerHtml == null || this.needUpdate) {
            this.update();
        }
        return this.outerHtml;
    }
    
    private void update() {
        StringBuilder html = new StringBuilder();
        
        StringBuilder attrs = new StringBuilder();
        for (String key : this.attributes.keySet()) {
            if (this.attributes.get(key) == null ||
                "".equals(this.attributes.get(key))) {
                continue;
            }
            attrs.append(key);
            attrs.append("='");
            attrs.append(this.attributes.get(key));
            attrs.append("' ");
        }
        attrs.deleteCharAt(attrs.length()-1);
        
        html.append('<');
        html.append(this.tagName);
        html.append(' ');
        html.append(attrs);
        html.append('>');
        
        if (this.hasBody) {
            html.append(this.innerHtml);
            html.append("</");
            html.append(this.tagName);
            html.append('>');
        }
        
        this.needUpdate = false;
        this.outerHtml = html.toString();
    }
    
}
