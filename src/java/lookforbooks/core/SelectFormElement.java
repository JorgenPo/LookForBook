/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookforbooks.core;

import java.util.ArrayList;
import java.util.List;
import lookforbooks.core.utils.DOMelement;

/**
 *
 * @author jorgen
 */
public class SelectFormElement extends FormElement {
    private List<DOMelement> options;
    private int selectedOptionIndex;
    
    public SelectFormElement(String name) {
        super("select", name, null);
        
        this.options = new ArrayList<>();
        this.selectedOptionIndex = -1;
    }
    
    public SelectFormElement addOption(String text) {
        DOMelement option = new DOMelement("option");
        option.setInnerHtml(text);
        option.set("value", this.name + "-" + (this.options.size()));
        
        this.options.add(option);
        this.dom.append(option);
        
        return this;
    }
    
    public List<String> getOptions() {
        ArrayList<String> o = new ArrayList<>();
        
        this.options.forEach((option) -> {
            o.add(option.getInnerHtml());
        });
        
        return o;
    }
    
    // Intersect parent's method
    @Override
    public String getValue() {
        return this.getSelected();
    }
    
    @Override
    public SelectFormElement setValue(String val) {
        String temp[] = val.split("-");
        val = temp[temp.length - 1];
        
        this.select(Integer.parseInt(val));
        return this;
    }
    
    public SelectFormElement removeOption(String text) {
        for (int i = 0; i < this.options.size(); ++i) {
            if ( this.options.get(i)
                    .getInnerHtml()
                    .equals(text) ) {
                this.removeOption(i);
            }
        }
        
        return this;
    }
    
    public SelectFormElement removeOption(int index) {
        this.options.remove(index);
        this.dom.find("option").get(index).remove();
        return this;
    }
    
    public SelectFormElement select(int i) {
        this.options.forEach((option) -> {
            option.set("selected", null);
        });
        this.options.get(i).set("selected", "true");
        this.selectedOptionIndex = i;
        
        return this;
    }
    
    public String getSelected() {
        return this.options
                .get(this.selectedOptionIndex)
                .getInnerHtml();
    }
    
}
