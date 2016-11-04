/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core;

/**
 *
 * @author jorgen
 */
public class TextAreaFormElement extends FormElement {
    private boolean readOnly;
    private boolean disabled;
    
    private String text;
    
    public TextAreaFormElement(String name) {
        super("textarea", name, null);
        
        this.readOnly = false;
        this.disabled = false;
        this.text = "";
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public TextAreaFormElement setDisabled(boolean disabled) {
        if (disabled) {
            this.set("disabled", "true");
        } else {
            this.set("disabled", "false");
        }
        
        this.disabled = disabled;
        return this;
    }
    
    public boolean isReadOnly() {
        return this.readOnly;
    }
    
    public TextAreaFormElement setReadOnly(boolean readOnly) {
        if (readOnly) {
            this.set("readonly", "true");
        } else {
            this.set("readonly", "false");
        }
        
        this.readOnly = readOnly;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.dom.setInnerHtml(text);
    }
    
}
