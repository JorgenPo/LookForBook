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
    APP: "/lookforbook",
    
    isDomLoaded: false,
    
    loadDOM: function () {
        this.manageLists();
        this.cart = document.getElementById("cart-items");
        this.cartWrap = document.getElementById("cart");
        this.cartEmpty = document.getElementById("cart-empty");
        this.cartCounter = document.querySelector("span.cart-count");
        
        this.isDomLoaded = true;
        
        this.makeClock();
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
        if ( !this.isDomLoaded ) {
            this.loadDOM();
        }
        
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
        var count = document.getElementById('cart-item-' + bookid)
            .getElementsByClassName('cart-item-count')[0];
        count = count.value;
        
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
              self.addToCounter(-parseInt(count));
          }  
        };
       
        req.send();
    },
    
    /* Not using now */
    changeBookVal(bookid, info) {
        if (info.value <= 0) {
            return this.removeBook(bookid);
        } 
    },
    
    addToCounter(num) {
         this.cartCounter.innerHTML 
            = parseInt(this.cartCounter.innerHTML) + num;
    },
    
    makeClock() {
        var clocks = document.querySelectorAll(".clock");

        setInterval(function() {
            for (var i = 0; i < clocks.length; ++i) {
                clocks[i].innerHTML = new Date().toTimeString();
            }
        }, 1000);
    },
    
    submitReview(form) {
        var text = form.elements["text"].value;
        
        var params = "text=" + encodeURIComponent(text);
        var req = new XMLHttpRequest();
        req.open("POST", this.APP + "/review?text=" + encodeURIComponent(text), true);
        
        var self = this;
        req.onreadystatechange = function() {
          if (req.readyState === 4 && req.status === 200) {
              form.elements["text"].value = "";
              self.addReview(text);
          }
        };
       
        req.send(params);
        
        return false;
    },
    
    addReview(review) {
        var list = document.getElementById("review-list");
        var items = list.querySelectorAll("tr").length;
        
        var item = document.createElement("tr");
        item.id = "review-" + (items + 1);
        
        var info = document.createElement("td");
        info.classList.add("review-info");
        
        var title = document.createElement("a");
        title.innerHTML = document.getElementById("username").innerHTML;
        
        var img = document.createElement("img");
        img.src = "getImage?image=/profile/avatar_small.png";
        
        var date = document.createElement("p");
        date.classList.add("review-date");
        date.innerHTML = new Date().toDateString();
        
        var text = document.createElement("td");
        text.classList.add("review-text");
        text.innerHTML = review;
        
        item.appendChild(info);
        item.appendChild(text);
        
        info.appendChild(title);
        info.appendChild(document.createElement("br"));
        info.appendChild(img);
        info.appendChild(date);
        
        list.appendChild(item);
    }
};