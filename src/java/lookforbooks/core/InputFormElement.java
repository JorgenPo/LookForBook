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
    public InputFormElement(String name) {
        super("input", name, null);
        this.set("type", "text");
    }
    
    public InputFormElement(String name, String type, Map<String, String> attrs) {
        super("input", name, attrs);
        this.set("type", "text");
    }
    
    @Override
    public boolean validate() {
        return true;
    }
    
}
