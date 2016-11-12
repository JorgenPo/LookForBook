/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import buisness.Book;
import java.util.Map;
import lookforbooks.core.Form;
import lookforbooks.core.InputFormElement;
import lookforbooks.core.SelectFormElement;
import lookforbooks.core.TextAreaFormElement;

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
        
        TextAreaFormElement description = new TextAreaFormElement("description");
        description.setId("form-description")
                .setLabel("Description");
                
        InputFormElement author = new InputFormElement("author");
        author.setId("form-author")
                .setLabel("Author");
        
        InputFormElement price = new InputFormElement("price");
        price.setId("form-price")
                .setLabel("Price");
        
        InputFormElement isbn = new InputFormElement("isbn");
        isbn.setId("form-isbn ")
                .setLabel("ISBN");
        
        SelectFormElement genre = new SelectFormElement("genre");
        genre.addOption("Classic")
                .addOption("Detective")
                .addOption("Farytail")
                .addOption("Adventure")
                .addOption("Sci-fi")
                .addOption("Fantasy")
                .addOption("Novel")
                .addOption("Professional")
                .select(0)
                .setId("form-genre")
                .setLabel("Genre");
        
        InputFormElement house = new InputFormElement("house");
        house.setId("form-house")
                .setLabel("Publish house");
        
        InputFormElement year = new InputFormElement("year");
        year.setType("number")
                .setId("form-year")
                .setLabel("Publish year");
        
        SelectFormElement language = new SelectFormElement("language");
        language.addOption("Русский")
                .addOption("English")
                .addOption("Deautsch")
                .select(0)
                .setId("form-language")
                .setLabel("Original language");
        
        InputFormElement stock = new InputFormElement("stock");
        stock.setType("number")
                .setId("form-stock")
                .setLabel("In stock");
        
        InputFormElement submit = new InputFormElement("submit");
        submit.setType("submit")
                .setId("form-submit")
                .setValue("Add book")
                .addClass("btn default");
                
        this.add(title).add(author)
                .add(description).add(price)
                .add(isbn).add(genre).add(language)
                .add(house).add(year).add(stock).add(submit);
    }

    public Book constructBook() {
        Book book = new Book();
        Map<String, String> values = this.getValues();
        
        try {
            book.setAuthor(values.get("author"));
            book.setDesc(values.get("description"));
            book.setGenre(values.get("genre"));
            book.setHouse(values.get("author"));
            book.setIsbn(values.get("isbn"));
            book.setLang(values.get("language"));
            book.setPrice(Integer.parseInt(values.get("price")));
            book.setStock(Integer.parseInt(values.get("stock")));
            book.setTitle(values.get("title"));
            book.setYear(Integer.parseInt(values.get("year")));
        } catch(NumberFormatException e) {
            return null;
        }
        
        return book;
    }
}
