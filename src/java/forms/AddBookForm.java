/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.util.ArrayList;

import lookforbooks.core.Form;

/**
 *
 * @author jorgen
 */
public class AddBookForm implements Form {
     private int price;
     private int isbn;
     private int year;
     private String desc;
     private String author;
     private String genre;
     private String lang;
     private String house;

    @Override
    public ArrayList<String> validate() {
        ArrayList<String> errors = new ArrayList<>();
        
        return errors;
    }

    @Override
    public void init() {
       
    }

    @Override
    public String render() {
       return "";
    }
}
