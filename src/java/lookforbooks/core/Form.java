/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lookforbooks.core.utils.DOMelement;

/**
 *
 * @author jorgen
 */
public abstract class Form {
    protected DOMelement dom;

    protected String id;
    protected boolean isVisible;
    protected List<String> classList;
    protected Map<String, String> attributes;
    protected Map<String, FormElement> elements;
    protected String method;
    protected String action;
    
    public Form() {
        this.id = null;
        this.isVisible = true;
        this.classList = new ArrayList<>();
        this.attributes = new TreeMap<>();
        this.elements = new TreeMap<>();
        this.method = "post";
        
        this.dom = new DOMelement("form");
        
        init();
    }
    
    /**
     * Initializate form
     * add form elements
     */
    public abstract void init();

    public String getId() {
        return id;
    }

    public Form setId(String id) {
        this.id = id;
        return this;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public Form setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    public List<String> getClassList() {
        return classList;
    }

    public Form setClassList(List<String> classList) {
        this.classList = classList;
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Form setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Map<String, FormElement> getElements() {
        return this.elements;
    }
    
    public List<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
         
        this.elements.keySet().forEach((key) -> {
            values.add((String) this.elements.get(key).getValue());
        });
        
        return values;
    }
    
    public Form setValue(String name, String value) {
        this.elements.get(name).setValue(name);
        return this;
    }
    
    public String getMethod() {
        return method;
    }

    public Form setMethod(String method) {
        this.method = method;
        return this;
    }
    
    public Form addClass(String name) {
        if (this.classList.indexOf(name) == -1) {
            this.classList.add(name);
        }
        return this;
    }
    
    public Form removeClass(String name) {
        this.classList.remove(name);
        return this;
    }

    public String getAction() {
        return action;
    }

    public Form setAction(String action) {
        this.action = action;
        return this;
    }
    
    public Form add(FormElement elem) {
        this.elements.put(elem.getName(), elem);
        return this;
    }
    
    public boolean validate() {
        boolean isValid = true;
        
        for (String key : this.elements.keySet()) {
            isValid = this.elements.get(key).validate() | isValid;
            if (!isValid) break;
        }
        
        return isValid;
    }
    
    public String render() {
        // Generate elements html
        StringBuilder html = new StringBuilder();
        for (String key : this.elements.keySet()) {
            html.append(this.elements.get(key).getHtml());
            html.append("<br>");
        }
        if (html.length() > 0) {
            html.deleteCharAt(html.length() - 1);
        }
        
        StringBuilder classes = new StringBuilder();
        for (String cls : this.classList) {
            classes.append(cls);
            classes.append(" ");
        }
        if (classes.length() > 0) {
            classes.deleteCharAt(classes.length() - 1);
        }
        
        for (String key : this.attributes.keySet()) {
            this.dom.set(key, this.attributes.get(key));
        }
        
        this.dom.set("id", id)
                .set("class", classes.toString());
        
        this.dom.setInnerHtml(html.toString());
        
        return this.dom.getHtml();
    }
}
