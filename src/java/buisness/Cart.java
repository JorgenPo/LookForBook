/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jorgen
 */
public class Cart {
    private ArrayList<Book> books;
    private Map<Book, Integer> booksCount;
    
    public Cart() {
        books = new ArrayList<>();
        booksCount = new HashMap<>();
    }
    
    public String getItemsCount() {
        return Integer.toString(books.size());
    }
    
    public ArrayList<Book> getItems() {
        return books;
    }
    
    public Integer getItemCount(Book book) {
        return booksCount.get(book);
    }
    
    public Cart addItem (Book book) {
        if (books.contains(book)) {
            booksCount.put(book, booksCount.get(book) + 1);
        } else {
            books.add(book);
            booksCount.put(book, 1);
        }
        
        return this;
    }
    
    public Cart removeItem (Book book) {
        if (books.contains(book)) {
            if (booksCount.get(book) == 1) {
                books.remove(book);
            }
            booksCount.put(book, booksCount.get(book) - 1);
        }
        
        return this;
    }
    
    public Invoice getInvoice() {
        return null;
    }
    
    public Boolean isEmpty() {
        return books.isEmpty();
    }
    
    public Integer getTotalPrice() {
        Integer total = 0;
        for (Book book : booksCount.keySet()) {
            total += book.getPrice() * booksCount.get(book);
        }
        
        return total;
    }
}
