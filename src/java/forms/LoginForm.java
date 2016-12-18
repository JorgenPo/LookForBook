/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import buisness.User;
import db.UsersDB;
import java.util.Map;
import lookforbooks.core.Form;
import lookforbooks.core.InputFormElement;

/**
 *
 * @author jorgen
 */
public class LoginForm extends Form {

    @Override
    public void init() {
        this.setAction("j_security_check")
                .setMethod("post")
                .setDescription("Please, enter your account information in fields bellow!"
                        + "After authorization you will be able to make orders and visit almost"
                        + "every page of this site!")
                .setTitle("Sign In to site");
        
        InputFormElement username = new InputFormElement("j_username");
        username.setType("text")
                .setLabel("Username");
        
        InputFormElement password = new InputFormElement("j_password");
        password.setType("password")
                .setLabel("Password");
        
        InputFormElement submit = new InputFormElement("submit");
        submit.setType("submit")
              .setValue("Up to the shop!")
              .addClass("btn default");
              
        this.add(username).add(password).add(submit);
        
    }
    
    public User constructUser() {
        Map<String, String> values = this.getValues();
        
        UsersDB db = new UsersDB();
        
        User user = null;
        user = db.getUserByEmail(values.get("j_username"));
        
        return user;
    }
}
