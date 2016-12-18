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
import lookforbooks.Translator;
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
    protected String value;
    
    protected boolean isVisible;
    protected boolean isRequired; //TRUE by default!
    
    protected List<String> classList;
    protected Map<String, String> attributes;

   
    public FormElement(String tagName, String name, Map<String, String> attrs) {
        this.name = name;
        this.id = null;
        this.value = null;
        
        this.isVisible = true;
        this.isRequired = true; 
        
        this.classList = new ArrayList<>();
        
        
        if (attrs == null) {
            this.attributes = new TreeMap<>();
        } else {
            this.attributes = attrs;
        }
        
        this.set("required", this.isRequired);
        
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

    public boolean isRequired() {
        return isRequired;
    }

    public FormElement setRequired(boolean isRequired) {
        this.isRequired = isRequired;
        this.set("required", isRequired);
        return this;
    }
    
    
    public boolean isVisible() {
        return isVisible;
    }

    public FormElement setVisible(boolean isVisible) {
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
    
    public FormElement set(String name, boolean value) {
        this.attributes.put(name, value ? "true" : "false");
        return this;
    }
    
    public String get(String name) {
        return this.attributes.get(name);
    }
    
    public Map<String, String> getAttributes() {
        return this.attributes;
    }
    
    public FormElement setLabel(String label) {
        label = Translator.getInstance().translate(label);
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
    
    public boolean validate() {
        return true;
    }
    
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

