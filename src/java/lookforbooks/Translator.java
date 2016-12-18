/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Translator class
 * Usage:
 *  Translator tr = new Translator(Locale locale); 
 *  tr.translate("Hello World") // Returns string translated to locale language
 * 
 * @author jorgen october 2016(c)
 */
public class Translator {
    ResourceBundle resources;
    
    static Translator instance = null;
    
    public static Translator getInstance() {
        if (instance == null) {
            instance = new Translator();
            Translator.setLocale(Locale.getDefault());
        }
        
        return instance;
    }
    
    public static void setLocale(Locale locale) {
        ResourceBundle.clearCache();
        if(instance == null) {
            getInstance();
        }
        instance.resources = ResourceBundle.getBundle("locale.Localization", locale);
    }
    
    public String translate(String string) {
        String translated;
        
        try {
            translated = resources.getString(string);
        } catch (Exception e) {
            translated = "***"+string+"***";
        } 
        
        return translated;
    }
    
    public String getLocale() {
        return resources.getLocale().getLanguage();
    }
}
