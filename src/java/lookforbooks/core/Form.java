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
    protected DOMelement domTitle;
    protected DOMelement domDescription;
    
    protected String id;
    protected boolean isVisible;
    protected List<String> classList;
    protected Map<String, String> attributes;
    protected List<FormElement> elements;
    protected String method;
    protected String action;
    
    public Form() {
        this.id = null;
        this.isVisible = true;
        this.classList = new ArrayList<>();
        this.attributes = new TreeMap<>();
        this.elements = new ArrayList<>();
        this.method = "post";
        
        this.dom = new DOMelement("form");
        this.domTitle = new DOMelement("h2");
        this.domDescription = new DOMelement("p");
        
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

    public List<FormElement> getElements() {
        return this.elements;
    }
    
    public List<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
         
        this.elements.forEach((elem) -> {
            values.add((String) elem.getValue());
        });
        
        return values;
    }
    
    public Form setValue(String name, String value) {
        for (FormElement elem : this.elements) {
           if (elem.getName().equals(name)) {
               elem.setValue(value);
               break;
           } 
        }
        
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
        this.elements.add(elem);
        return this;
    }

    public String getDescription() {
        return this.domTitle.getInnerHtml();
    }

    public Form setDescription(String description) {
        this.domDescription.setInnerHtml(description);
        return this;
    }

    public String getTitle() {
        return this.domTitle.getInnerHtml();
    }

    public Form setTitle(String title) {
        this.domTitle.setInnerHtml(title);
        return this;
    }
    
    
    
    public boolean validate() {
        boolean isValid = true;
        
        for (FormElement elem : this.elements) {
            isValid = elem.validate() | isValid;
            if (!isValid) break;
        }
        
        return isValid;
    }
    
    public String render() {
        // Generate elements html
        this.dom.setInnerHtml("");
        
        if (!this.domTitle.isEmpty()) {
            this.dom.append(this.domTitle);
        }
        if (!this.domDescription.isEmpty()) {
            this.dom.append(this.domDescription);
        }
        
        this.elements.forEach((elem) -> {
            this.dom.append(elem.getDom());
            this.dom.append("br");
        });
        
        this.dom.getLastChild().remove();
        
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
        
        this.dom.set("id", this.id)
                .set("method", this.method)
                .set("class", classes.toString());
        
        return this.dom.getHtml();
    }
}
