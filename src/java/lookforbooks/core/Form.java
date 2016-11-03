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

/**
 *
 * @author jorgen
 */
public abstract class Form {
    private String id;
    private boolean isVisible;
    private List<String> classList;
    private Map<String, String> attributes;
    private List<FormElement> elements;
    private String method;
    
    
    public Form() {
        this.id = null;
        this.isVisible = true;
        this.classList = new ArrayList<>();
        this.attributes = new TreeMap<>();
        this.elements = new ArrayList<>();
        this.method = "post";
        
        init();
    }
    
    /**
     * Initializate form
     * add form elements
     */
    public abstract void init();

    protected String getId() {
        return id;
    }

    protected Form setId(String id) {
        this.id = id;
        return this;
    }

    protected boolean isIsVisible() {
        return isVisible;
    }

    protected Form setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    protected List<String> getClassList() {
        return classList;
    }

    protected Form setClassList(List<String> classList) {
        this.classList = classList;
        return this;
    }

    protected Map<String, String> getAttributes() {
        return attributes;
    }

    protected Form setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    protected List<FormElement> getElements() {
        return elements;
    }

    protected Form setElements(List<FormElement> elements) {
        this.elements = elements;
        return this;
    }

    protected String getMethod() {
        return method;
    }

    protected Form setMethod(String method) {
        this.method = method;
        return this;
    }
    
    /**
     * Validate form
     * @return list of errors (as Strings)
     */
    public abstract ArrayList<String> validate();
    
    public abstract String render();
}
