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
    APP: "/LookForBook-war",
    
    loadDOM: function () {
        this.manageLists();
        this.cart = document.getElementById("cart-items");
        this.cartWrap = document.getElementById("cart");
        this.cartEmpty = document.getElementById("cart-empty");
        this.cartCounter = document.querySelector("span.cart-count");
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
    },
    
    addBook(book) {
        var bookid = book.getAttribute("bookId");

        var req = new XMLHttpRequest();
        req.open("POST", this.APP + "/cart?add=" + bookid, true);
        
        var self = this;
        req.onreadystatechange = function() {
          if (req.readyState === 4 && req.status === 200) {
              self.cartCounter.innerHTML = parseInt(self.cartCounter.innerHTML) + 1;
              alert("Book has been added to cart!");
          }  
        };
        
        req.send();
    },
    
    removeBook(bookid) {
        var req = new XMLHttpRequest();
        req.open("POST", this.APP + "/cart?remove=" + bookid, true);
        
        var self = this;
        req.onreadystatechange = function() {
          if (req.readyState === 4 && req.status === 200) {
              var row = document.getElementById("cart-item-" + bookid);
              if (row) {
                  row.remove();
                  if (self.cart.children.length === 0) {
                      self.cartWrap.remove();
                      self.cartEmpty.style.display = "block";
                  }
              }
          }  
        };
        
        req.send();
    }
};