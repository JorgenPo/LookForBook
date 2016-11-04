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
        this.setId("add-book")
                .setTitle("Submit book")
                .setAction("add");
        
        this.setDescription(
            "Please, enter book information and"
            + " choose cover photo. Book will be added"
            + " after moderation!");
        
        // Elements declaration
        InputFormElement title = new InputFormElement("title");
        title.setId("form-title")
                .setLabel("Title");
        
        InputFormElement author = new InputFormElement("author");
        author.setId("form-author")
                .setLabel("Description");
        
        InputFormElement price = new InputFormElement("price");
        price.setId("form-price")
                .setLabel("Price");
        
        InputFormElement isbn = new InputFormElement("isbn");
        isbn.setId("form-isbn ")
                .setLabel("ISBN");
        
        InputFormElement house = new InputFormElement("house");
        house.setId("form-house")
                .setLabel("Publish house");
        
        InputFormElement year = new InputFormElement("year");
        year.setId("form-year")
                .setLabel("Publish year")
                .set("type", "number");
                
        InputFormElement submit = new InputFormElement("submit");
        submit.setId("form-submit")
                .setValue("Add book")
                .set("type", "submit")
                .addClass("btn default");
        
        this.add(title)
                .add(author).add(price)
                .add(isbn).add(house)
                .add(year).add(submit);
    }

    @Override
    public boolean validate() {
       return true; // TODO: validation
    }

}
