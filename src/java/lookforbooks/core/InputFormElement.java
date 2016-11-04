/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core;

import java.util.Map;

/**
 *
 * @author jorgen
 */
public class InputFormElement extends FormElement {
    private String type;
    
    public InputFormElement(String name) {
        super("input", name, null);
        this.type = "text";
        
        this.set("type", this.type);
    }
    
    public InputFormElement(String name, String type, Map<String, String> attrs) {
        super("input", name, attrs);
        this.set("type", "text");
    }

    public String getType() {
        return type;
    }

    public InputFormElement setType(String type) {
        this.type = type;
        this.set("type", this.type);
        return this;
    }
   
}
