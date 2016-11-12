/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    Item.loadDOM();
    Item.setDefault(defaultPage);
    console.log("LOADED");
};

var Item = {
    loadDOM: function() {
        this.fullDesc = document.getElementById("full-desc");
        this.shortDesc = document.getElementById("short-desc");
        this.reviews = document.getElementById("reviews");
        this.fullDescLi = document.getElementById("full-desc-li");
        this.shortDescLi = document.getElementById("short-desc-li");
        this.reviewsLi = document.getElementById("reviews-li");
    },
    
    setDefault: function(id) {
        switch(id) {
            case 0:
                this.shortDescLi.click();
                break;
            case 1:
                this.fullDescLi.click();
                break;
            case 2:
                this.reviewsLi.click();
                break;
            default:
                this.shortDescLi.click();
                break;
        }
    },
    
    showFullDesc: function() {
        this.shortDesc.style.display = "none";
        this.reviews.style.display = "none";
        this.fullDesc.style.display = "block";
        this.shortDescLi.classList.remove("active");
        this.reviewsLi.classList.remove("active");
        this.fullDescLi.classList.add("active");
    },

    showShortDesc: function() {
        this.shortDesc.style.display = "block";
        this.reviews.style.display = "none";
        this.fullDesc.style.display = "none";
        this.shortDescLi.classList.add("active");
        this.reviewsLi.classList.remove("active");
        this.fullDescLi.classList.remove("active");
    },

    showReviews: function() {
        this.shortDesc.style.display = "none";
        this.reviews.style.display = "block";
        this.fullDesc.style.display = "none";
        this.shortDescLi.classList.remove("active");
        this.reviewsLi.classList.add("active");
        this.fullDescLi.classList.remove("active");
    }
}