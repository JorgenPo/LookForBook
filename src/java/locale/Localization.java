/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locale;

import java.util.ListResourceBundle;

/**
 *
 * @author jorgen
 */
public class Localization extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"Look for book - thats how we do it!", "Look for book - thats how we do it!"},
            {"No title provided", "No title provided"}, 
            {"*Curency*", "$"},
            {"Items on stock", "Items on stock"},
            {"Add item to cart", "Add item to cart"},
            {"Did not find a book? Add your own!", "Did not find a book? Add your own!"},
            {"Find", "Find"},
            {"enter book name or author", "enter book name or author"},
            {"Look For Book", "Look For Book"},
            {"No search results by query", "No search results by query"},
            {"Confused by that range? Just", "Confused by that range? Just"},
            {"Find a book", "Find a book"},
            {"Hello, guest!", "Hello, guest!"},
            {"SignIn", "Sign In"},
            {"SignUp", "Sign Up"},
            {"or", "or"},
            {"In stock. Available:", "In stock. Available:"},
            {"ea.", "ea."},
            {"Not in stock now.", "Not in stock now."},
            {"Order it now!", "Order it now!"},
            {"Add item to cart", "Add item to cart"},
            {"Short description", "Short description"},
            {"Full description", "Full description"},
            {"Customers reviews", "Customers reviews"},
            {"read first pages", "read first pages"},
            {"Share to", "Share to"},
            {"Choose item type", "Choose item type"},
            {"Paperback", "Paperback"},
            {"E-book", "E-book"},
            {"Know better offer?", "Know better offer?"},
            {"Shipping to your country is available! Shipping by RuPostal service - will be in 14 - 60 days. Buyer protection enabled!", "Shipping to your country is available! Shipping by RuPostal service - will be in 14 - 60 days. Buyer protection enabled!"},
            {"Choose the language", "Choose the language"},
            {"Audio-book", "Audio-book"},
            {"Audio-book", "Audio-book"}
        };
    }
    
}
