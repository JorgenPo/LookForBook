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
 * Interface for form element 
 * 
 * @author jorgen
 */
public abstract class FormElement {
    protected DOMelement dom;
    protected DOMelement domLabel;
    
    protected String id;
    protected String name;
    protected boolean isVisible;
    protected List<String> classList;
    protected Map<String, String> attributes;
    protected String value;
    
    public FormElement(String tagName, String name, Map<String, String> attrs) {
        this.name = name;
        this.id = null;
        this.isVisible = true;
        this.classList = new ArrayList<>();
        this.value = null;
        
        if (attrs == null) {
            this.attributes = new TreeMap<>();
        } else {
            this.attributes = attrs;
        }
        
        this.dom = new DOMelement(tagName);
        this.domLabel = new DOMelement("label");
    }
    
    public String getId() {
        return id;
    }

    public FormElement setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FormElement setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public FormElement setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }
    
    public FormElement setAttributes(Map<String, String> attrs) {
        this.attributes = attrs;
        return this;
    }
    
    public FormElement set(String name, String value) {
        this.attributes.put(name, value);
        return this;
    }
    
    public String get(String name) {
        return this.attributes.get(name);
    }
    
    public Map<String, String> getAttributes() {
        return this.attributes;
    }
    
    public FormElement setLabel(String label) {
        this.domLabel.setInnerHtml(label);
        return this;
    }
    
    public String getLabel() {
        return this.domLabel.getInnerHtml();
    }
    
    public String getValue() {
        return this.value;
    }
    
    public FormElement setValue(String val) {
        this.value = val;
        return this;
    }
    
    public FormElement addClass(String name) {
        if (this.classList.indexOf(name) == -1) {
            this.classList.add(name);
        }
        return this;
    }
    
    public FormElement removeClass(String name) {
        this.classList.remove(name);
        return this;
    }
    
    public List<String> getClassList() {
        return this.classList;
    }
    
    public abstract boolean validate();
    
    public DOMelement getDom() {
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
                .set("class", classes.toString())
                .set("name", this.name)
                .set("value", this.value);
                
        return this.dom;
    }
    
    public DOMelement getLabelDom() {
        if (this.id != null) {
            this.domLabel.set("for", this.id);
        }
        
        return this.domLabel;
    }
}

