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
 * Interface for form element 
 * 
 * @author jorgen
 * @param <T> Form element type
 */
public abstract class FormElement<T> {
    private String id;
    private String name;
    private String label;
    private boolean isVisible;
    private List<String> classList;
    private Map<String, String> attributes;
    private T value;

    public FormElement(String name, Map<String, String> attrs) {
        this.name = name;
        this.id = null;
        this.label = this.name;
        this.isVisible = true;
        this.classList = new ArrayList<>();
        this.value = null;
        
        if (attrs == null) {
            this.attributes = new TreeMap<>();
        } else {
            this.attributes = attrs;
        }
    }
    
    public String getId() {
        return id;
    }

    public FormElement<T> setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FormElement<T> setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public FormElement<T> setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }
    
    public FormElement<T> setAttributes(Map<String, String> attrs) {
        this.attributes = attrs;
        return this;
    }
    
    public Map<String, String> getAttributes() {
        return this.attributes;
    }
    
    public FormElement<T> setLabel(String label) {
        this.label = label;
        return this;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public FormElement<T> setValue(T val) {
        this.value = val;
        return this;
    }
    
    public FormElement<T> addClass(String name) {
        if (this.classList.indexOf(name) == -1) {
            this.classList.add(name);
        }
        return this;
    }
    
    public FormElement<T> removeClass(String name) {
        this.classList.remove(name);
        return this;
    }
    
    public List<String> getClassList() {
        return this.classList;
    }
    
    public abstract String validate();
    
    public abstract String getHtml();
}

