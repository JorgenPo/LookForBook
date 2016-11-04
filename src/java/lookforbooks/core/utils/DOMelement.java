/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jorgen
 */
public class DOMelement {
    protected DOMelement parent;
    protected List<DOMelement> children;
    
    protected String tagName;
    protected Map<String, String> attributes;
    protected String innerHtml;
    protected String outerHtml;
    protected boolean hasBody;
    
    private boolean needUpdate;
    private boolean needInnerUpdate;
    
    public DOMelement(String tagName) {
        this.tagName = tagName;
        this.attributes = new TreeMap<>();
        this.innerHtml = "";
        this.outerHtml = "";
        
        // There are tags with no body
        this.hasBody = tagName.equals("input") |
                       tagName.equals("link") |
                       tagName.equals("br") |
                       tagName.equals("hr");
        this.hasBody = !this.hasBody;
        
        this.needUpdate = true;
        this.needInnerUpdate = false;
        
        this.children = new ArrayList<>();
        this.parent = null;
    }
    
    public DOMelement append(DOMelement element) {
        if (!this.hasBody) {
            return this; // TODO: throw!
        }
        
        element.setParent(this);
        this.children.add(element);
        
        this.needInnerUpdate = true;
        
        return this;
    }
    
    public List<DOMelement> find(String tagName) {
        if (!this.hasBody) {
            return null; //TODO: throw an error!
        }
        
        ArrayList<DOMelement> results = new ArrayList<>();
        this.children.forEach((child) -> {
            if (child.getTagName().equals(tagName)) {
                results.add(child);
            }
        });
        
        return results;
    }
    
    public DOMelement append(String tagName) {
        DOMelement element = new DOMelement(tagName);
        return this.append(element);
    }
    
    public List<DOMelement> getChildren() {
        if (!this.hasBody) {
            return null; //TODO: throw an error!
        }
        
        return this.children;
    }
    
    public DOMelement getChild(int index) {
        if (!this.hasBody) {
            return this; //TODO: throw an error!
        }
        
        DOMelement child;
        try {
            child = this.children.get(index);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
        
        return child;
    }
    
    public DOMelement getFirstChild() {
        if (!this.hasBody) {
            return this; //TODO: throw an error!
        }
        
        return this.getChild(0);
    }
    
    public DOMelement getLastChild() {
        if (!this.hasBody) {
            return this; //TODO: throw an error!
        }
        
        return this.getChild(this.children.size() - 1);
    }
    
    public DOMelement removeChild(DOMelement child) {
        if (!this.hasBody) {
            return this; //TODO: throw an error!
        }
        
        if (this.children.remove(child)) {
            this.needInnerUpdate = true;
        }
        
        return this;
    }
    
    public void remove() {
        this.children.forEach((child) -> {
            child.setParent(this.parent); // I'm gone - now your parent is my parent
        });
        
        if (this.parent != null) {
            this.parent.removeChild(this);
        }
    }
    
    public DOMelement setParent(DOMelement parent) {
        this.parent = parent;
        return this;
    }
    
    public DOMelement set(String name, String value) {
        if (this.attributes.get(name) != null &&
           !this.attributes.get(name).equals(value)) {
            this.needUpdate = true;
        }
        
        this.attributes.put(name, value);
        return this;
    }
    
    public String getInnerHtml() {
        if (!this.hasBody) {
            return null; //TODO: throw an error!
        }
        return this.innerHtml;
    }
    
    public DOMelement setInnerHtml(String html) {
        if (!this.hasBody) {
            return this; //TODO: throw an error!
        }
        
        this.innerHtml = html;
        return this;
    }

    public String getTagName() {
        return tagName;
    }
    
    public boolean isEmpty() {
        if (!this.hasBody) {
            return true;
        }
        
        return this.innerHtml.isEmpty();
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
        if (attrs.length() != 0) {
            attrs.deleteCharAt(attrs.length()-1);
        }
        
        html.append('<');
        html.append(this.tagName);
        html.append(' ');
        html.append(attrs);
        html.append('>');
        
        if (this.hasBody) {
            if (this.needInnerUpdate) {
                this.updateInner();
            }
            html.append(this.innerHtml);
            html.append("</");
            html.append(this.tagName);
            html.append('>');
        }
        
        this.needUpdate = false;
        this.outerHtml = html.toString();
    }
    
    private void updateInner() {
        StringBuilder html = new StringBuilder();
        this.children.forEach((child) -> {
            html.append(child.getHtml());
        });
        
        this.innerHtml = html.toString();
    }
   
}
