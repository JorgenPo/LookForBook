
/*
 * FronteX - front end widgets set for web applications
 * 
 * PREREQUIRES:
 * JavaScript 2016 (modern browsers)
 * 
 */

/*
 * ===================
 *      Widgets
 * ===================
 */

/*
 * LIST WIDGET - showable / hiddeble list 
 * 
 * Usage:
 * 
 * ...
 * <a class='list-trigger' target='mylist'></a>
 * <div id='mylist' class='list-body'>
 *   <p>Imma noob</p>
 * </div>
 * 
 * var frontex = new FronteX();
 * 
 * window.onload = function() {
 *  frontex.init(); <b>Automaticly parse all elements </b>
 * });
 * 
 * BUGS:
 * 
 * TIPS:
 * ! List trigger must contain target attribute
 *
 * SEE ALSO:
 * 
 */

class FronteX {
    constructor() {
        this.trigClass = 'list-trigger';
        this.bodyClass = 'list-body';
    }
    
    init() {
        setTimeout( () => { // For case when dom wasn't loaded yet
            this.initLists();
        }, 1000 ).bind(this);
    }
    
    initLists() {
        var toogles = document.querySelectorAll(this.trigClass);
        var lists = {};

        toogles.forEach(function (toogle) {
            lists[toogle] = document.getElementById(toogle.getAttribute("target"));
            
            lists[toogle].style.position = "absolute";
            var el;
            var timer;
            toogle.onmouseover = function () {
                clearTimeout(timer);
                el = lists[toogle];
                el.style.display = "block";
            };
            toogle.onmouseout = function () {
                timer = setTimeout(function () {
                    el.style.display = "none";
                }, 250);
            };
            lists[toogle].onmouseover = function () {
                clearTimeout(timer);
            };
            lists[toogle].onmouseout = function () {
                timer = setTimeout(function () {
                    el.style.display = "none";
                }, 250);
            };
        });
    }
}

