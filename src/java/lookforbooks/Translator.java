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
    ResourceBundle resources = null;
    
    public Translator(Locale locale) {
        System.out.println(locale);
        ResourceBundle.clearCache();
        resources = ResourceBundle.getBundle("locale.Localization", locale);
    }
    
    public String translate(String string) {
        return resources.getString(string);
    }
}
