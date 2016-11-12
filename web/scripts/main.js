/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    Look.loadDOM();
    console.log("LOADED");
};

var Look = {
    loadDOM: function() {
        
    },
    
    changeLanguage: function(sel) {
        var url = sel.options[sel.selectedIndex].value;
        
        X
        document.location.href = url;
    }   
}