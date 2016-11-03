<%-- 
    Document   : item
    Created on : 23.10.2016, 20:45:31
    Author     : jorgen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${tr.translate("Look For Book")}: ${item.title}</title>
        <link href="styles/main.css" rel="stylesheet">
        <link href="styles/item.css" rel="stylesheet">
        <link href="styles/header.css" rel="stylesheet">
        <link href="styles/search.css" rel="stylesheet">
        <link href="styles/auth.css" rel="stylesheet">
        <link href="styles/footer.css" rel="stylesheet">
        
        <script type="text/javascript" src="scripts/item.js"></script>
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        
        <div class="container">
            <aside class="image-block pull-left">
                <img class="image-thumb" src="getImage?image=items/${item.id}.jpg"
                     alt="${item.title}"
                     width="220px" height="344px"
                     onload="document.querySelector('.image-back').style = 'background: rgba(0, 0, 0, 0.4);'"/>
                <div class="image-back"></div>
                <a class="image-firstpages" href="#">${tr.translate("read first pages")}</a>
            </aside>

            <section class="main-content">
                <h2 class="item-title">${item.title}</h2><span class="item-rating">9.8 / 10</span>
                <p class="item-author">by <a href="#">${item.author}</a></p>
                
                <p class="item-share">${tr.translate("Share to")}:</p>
                <ul class="share-list menu horizontal">
                        <li><a href="#"><img src="getImage?image=icons/fb_icon.png" alt="facebook" /></a></li>
                        <li><a href="#"><img src="getImage?image=icons/tw_icon.png" alt="twitter" /></a></li>
                        <li><a href="#"><img src="getImage?image=icons/gp_icon.png" alt="vkontakte" /></a></li>
                        <li><a href="#"><img src="getImage?image=icons/im_icon.png" alt="instagram" /></a></li>
                </ul>
                
                <div class="item-type">
                    <p>${tr.translate("Choose item type")}:</p>
                    <ul>
                        <li>${tr.translate("Paperback")} <input type="checkbox" name="paperback"/></li>
                        <li>${tr.translate("E-book")} <input type="checkbox" name="ebook"/></li>
                        <li>${tr.translate("Audio-book")} <input type="checkbox" name="audio"/></li>
                    </ul>
                </div>
                
                <hr>
                
                <section class="info">
                    <nav class="info-type menu horizontal">
                        <ul>
                            <li id="short-desc-li" onclick="Item.showShortDesc()" class="active">${tr.translate("Short description")}</li>
                            <li id="full-desc-li" onclick="Item.showFullDesc()">${tr.translate("Full description")}</li>
                            <li id="reviews-li" onclick="Item.showReviews()">${tr.translate("Customers reviews")}</li>
                        </ul>
                    </nav>
                    <div class="info-body">
                        <div id="short-desc" style="display:none;"> 
                            <p class="item-description">${item.desc}</p>
                        </div>
                        <div id="full-desc" style="display: none;">
                            <label>${tr.translate("ISBN")}: </label>${item.isbn}<br>
                            <label>${tr.translate("Price")}: </label>${item.price}<br>
                            <label>${tr.translate("Pages")}: </label>500<br>
                            <label>${tr.translate("Genre")}: </label>${tr.translate("Classic")}<br>
                            <label>${tr.translate("Rating")}: </label>9/10<br>
                            <br>
                            <p class="item-description">${item.desc}</p>
                        </div>
                        <div id="reviews" style="display: none;">
                            
                        </div>
                    </div>
                </section>
                    
                <section class="similiar-items">
                    <!--<%@ include file="/WEB-INF/modules/header.jspf" %>-->
                </section>
            </section>
           
            <aside class="right-block pull-right">
                <%@ include file="/WEB-INF/modules/language.jspf" %>
                <div class="buy-info">
                    <p class="item-price">${item.price}${tr.translate("*Curency*")}</p>
                    <a class="item-price-notbest hint">${tr.translate("Know better offer?")}</a>
                    <c:if test="${item.stock > 0}">
                        <p class="item-available">
                            ${tr.translate("In stock. Available:")}
                             ${item.stock}
                            ${tr.translate("ea.")}
                        </p>
                    </c:if>
                    <c:if test="${item.stock == 0}">
                        <p class="item-unavailable">
                            ${tr.translate("Not in stock now.")}<br>
                            <a href="#">${tr.translate("Order it now!")}</a>
                        </p>
                    </c:if>

                    <ol>
                        <li>
                            ${tr.translate("Paperback")} 
                            <input type="number" min="0" max="${item.stock}" value="1"> 
                            ${tr.translate("ea.")}
                        </li>
                    </ol>

                    <p class="item-ship-info">
                        ${tr.translate("Shipping to your country is available! Shipping by RuPostal service - will be in 14 - 60 days. Buyer protection enabled!")}
                    </p>
                    <button id="add-item" class="original">${tr.translate("Add item to cart")}</button>
                </div>
            </aside>
        </div>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
        <script>
            window.defaultPage = ${defaultPage};
        </script>
    </body>
</html>
