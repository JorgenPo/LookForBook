/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness;

import db.BooksDB;
import java.util.ArrayList;

/**
 *
 * @author jorgen
 */
public class Cart {
    private Invoice invoice;
    
    public Cart() {
        invoice = new Invoice();
    }
    
    public String getItemsCount() {
        int total = 0;
        
        for (LineItem item : invoice.getLineItems()) {
            total += item.getQuantity();
        }
        
        return String.valueOf(total);
    }
    
    public ArrayList<Book> getItems() {
        ArrayList<Book> list = new ArrayList<>();
        
        BooksDB db = new BooksDB();
        for (LineItem item : invoice.getLineItems()) {
            list.add(db.getBookById(item.getBookId()));
        }
        
        return list;
    }
    
    public Integer getItemCount(Book book) {
        
        for (LineItem line : invoice.getLineItems()) {
            if (line.getBookId() == book.getId()) {
                return line.getQuantity();
            }
        }
        
        return 0;
    }
    
    public Cart addItem (Book book) {
        return addItem(book, 1);
    }
    
    public Cart addItem (Book book, int q) {
        for (LineItem line : invoice.getLineItems()) {
            if (line.getBookId() == book.getId()) {
                line.setQuantity(line.getQuantity() + q);
                return this;
            }
        }
        
        LineItem item = new LineItem(invoice, book.getId(), q);
        invoice.getLineItems().add(item);
        
        return this;
    }
    
    public Cart removeItem (Book book) {
        for (LineItem line : invoice.getLineItems()) {
            if (line.getBookId() == book.getId()) {
                line.setQuantity(line.getQuantity() - 1);
                
                if (line.getQuantity() == 0) {
                    invoice.getLineItems().remove(line);
                }
                
                break;
            }
        }
        
        return this;
    }
   
    public Cart deleteItem (Book book) {
        for (LineItem line : invoice.getLineItems()) {
            if (line.getBookId() == book.getId()) {
                invoice.getLineItems().remove(line); 
                break;
            }
        }
        
        return this;
    }
    
    public Invoice getInvoice() {
        return null;
    }
    
    public Boolean isEmpty() {
        return invoice.getLineItems().isEmpty();
    }
    
    public Integer getTotalPrice() {
        Integer total = 0;
        

        // SLOWLY!!!
        for (Book b : this.getItems()) {
            total += this.getItemCount(b) * b.getPrice();
        }
        
        return total;
    }
}
