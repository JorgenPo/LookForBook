/* 
 * Copyright (C) 2016 jorgen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


window.onload = function () {
    Order.loadDOM();
};

var Order = {
    activeToggle: 1,
    APP: "/lookforbook",
    
    loadDOM: function() {
        this.steps = [];
        
        var toggles = document.getElementsByClassName("toggle"); 
        var toggleBodies = document.getElementsByClassName("body");
        
        for (var i = 0; i < toggles.length; ++i) {
            this.steps[i] = {
                toggle: toggles[i],
                body: toggleBodies[i]
            };
        }
        
        this.updateToggles();
        
        this.nextButton = document.getElementById("order-next");
        this.prevButton = document.getElementById("order-prev");
        
        // Delivery page
        this.currierCheck = document.getElementById("deliver-currier");
        this.shopCheck    = document.getElementById("deliver-shop");
        this.shopMap      = document.getElementById("deliver-map");
        this.shopName     = document.getElementById("deliver-shop-name");
        
        this.address = "unknown";
        ymaps.ready(this.loadMap);
    },
    
    updateToggles: function() {
        var step;
        for (var i = 0; i < this.steps.length; ++i) {
            step = this.steps[i];
            if (i === this.activeToggle - 1) {
                step.body.style.display = "block";
                step.toggle.classList.add("active");
            } else {
                step.body.style.display = "none";
                step.toggle.classList.remove("active");
            }
        }
    },
    
    loadMap: function() {
        var map = new ymaps.Map('deliver-map', {
            center: [59.94115597, 30.31073189],
            zoom: 10
        });
        
        var shops = [];
        
        shops.push(new ymaps.Placemark([59.98617620, 30.30401998], {
           hintContent: 'Магазин №1',
           balloonContent: 'Магазин на черной речке. \n\
                            Огромный выбор.\n\
                            Самый старый магазин.'
        }));
        
        shops.push(new ymaps.Placemark([59.92148721, 30.35505487], {
           hintContent: 'Магазин №2',
           balloonContent: 'Магазин на лиговском. \n\
                            Огромный выбор.\n\
                            Самый старый магазин.'
        }));
        
        shops.push(new ymaps.Placemark([59.93575194, 30.32162964], {
           hintContent: 'Магазин №3',
           balloonContent: 'Магазин на невском. \n\
                            Огромный выбор.\n\
                            Самый старый магазин.'
        }));
        
        var self = this;
        for (var i = 0; i < shops.length; ++i) {
            shops[i].events.add("click", function(e) {
                var shop = e.get('target');
                
                Order.shopName.innerHTML = shop.properties.get("hintContent");
                Order.shopName.setAttribute("value", i);
            });
            map.geoObjects.add(shops[i]);
        }
    },
    
    nextStep: function() {
        if ( this.steps.length < this.activeToggle + 1 ) {
            this.finishOrder();
            return;
        }
        
        ++this.activeToggle;
        
        if ( this.activeToggle === this.steps.length ) {
            this.setFinalResults();
           
        }
        
        this.updateToggles();
    },
        
    setFinalResults: function() {
            
        var method;
        
        if (this.shopName.innerHTML.length > 0) {
            this.address = this.shopName.innerHTML;
            method = document.getElementById("delivery-shop-label").innerHTML;
        } else {
            this.address = document.getElementById("address").innerHTML;
            method = document.getElementById("delivery-currier-label").innerHTML;
        }

        document.getElementById("deliver-address").innerHTML = this.address;
        document.getElementById("deliver-method").innerHTML = method;
    },
    
    prevStep: function() {
        if ( this.activeToggle === 1 ) {
            return;
        }
        
        --this.activeToggle;
        
        this.updateToggles();
    },
    
    finishOrder: function() {
        var req = new XMLHttpRequest();
        req.open("GET", this.APP + "/order?complete=true&address=" 
                + encodeURIComponent(this.address), false);
        
        var self = this;
        req.onreadystatechange = function() {
          if (req.readyState === 4 && req.status === 200) {
              alert("Поздравляем с покупкой!");
              
              window.location.href = self.APP + "/books";
          }  
        };
       
        req.send();
    },
    
    setDeliverType: function(type) {
        if ( type === 1 ) { // Currier deliver
            this.shopCheck.checked = false;
            this.shopMap.style.display = "none";
        } else {
            this.currierCheck.checked = false;
            this.shopMap.style.display = "block";
        }
    }
};