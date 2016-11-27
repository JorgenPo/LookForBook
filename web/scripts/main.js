/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Helper = {
    setCookie: function(name, value, expires, path, domain, secure) {
        document.cookie = name + "=" + escape(value) +
                ((expires) ? "; expires=" + expires : "") +
                ((path) ? "; path=" + path : "") +
                ((domain) ? "; domain=" + domain : "") +
                ((secure) ? "; secure" : "");
    }
};

window.onload = function () {
    Look.loadDOM();
    console.log("LOADED");
};

var Look = {
    loadDOM: function () {
        this.manageLists();
    },

    changeLanguage: function (sel) {
        var lang = sel.options[sel.selectedIndex].value;

        Helper.setCookie("lang", lang);
        window.location.reload(true);
    },
    
    manageLists: function() {
        var toogles = document.querySelectorAll(".list-toggle");
        var lists = {};
        
        console.log(toogles);
        toogles.forEach(function(toogle) {
            console.dir(toogle);
            lists[toogle] = document.getElementById(toogle.getAttribute("list"));
            
            var display, el;
            var timer;
            toogle.onmouseover = function() {
                clearTimeout(timer);
                el = lists[toogle];
                el.style.display = "block";
            };
            toogle.onmouseout = function() {
                timer = setTimeout(function() {
                   el.style.display = "none"; 
                }, 250);
            };
            lists[toogle].onmouseover = function() {
                clearTimeout(timer);
            };
            lists[toogle].onmouseout = function() {
                timer = setTimeout(function() {
                   el.style.display = "none"; 
                }, 250);
            };
        });
    }
};