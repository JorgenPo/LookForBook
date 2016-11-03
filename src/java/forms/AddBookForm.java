/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import lookforbooks.core.Form;
import lookforbooks.core.InputFormElement;

/**
 *
 * @author jorgen
 */
public class AddBookForm extends Form {
    
    @Override
    public void init() {
        this.setId("addBookForm")
                .setMethod("post");
        
        // Elements declaration
        InputFormElement name = new InputFormElement("name");
        name.set("placeholder", "Enter something!");
        
        this.add(name);
    }

    @Override
    public boolean validate() {
       return true; // TODO: validation
    }

}
