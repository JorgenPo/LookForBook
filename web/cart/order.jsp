
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${tr.translate("Look for book - thats how we do it!")}</title>
        <link href="<c:url value='/styles/main.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/index.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/header.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/search.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/footer.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/cart.css'/>" rel="stylesheet">
                
        <script type="text/javascript" src="scripts/main.js"></script>
        <script type="text/javascript" src="scripts/order.js"></script>
        <script src="https://api-maps.yandex.ru/2.1/?lang=tr_TR" type="text/javascript"></script>
                
        <style>
            .steps {
                margin-bottom: 20px;
                margin-top: 10px;
            }
            .steps > li {
                margin: 0 20px;
            }
            .toggle {
                margin-top: 15px; 
            }
            .body { 
                text-align: left;
                height: 200px;
            }
            #delivery-body {
                min-height: 250px;
            }
            .step-info {
                margin-bottom: 10px;
                text-align: center;
                font-weight: bold;
            }
            
            .step-info icon {
                margin-right: 5px;
            }
            
            #deliver-map {
                padding: 10px;
                width: 400px;
                height: 300px;
            }
            
            #deliver-shop-name {
                
            }
            
            #delivery-main {
                padding-left: 20px;
                padding-top: 20px;
            }
            
            #order-controls {
                clear: both;
            }
        </style>
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section class="main-content">
            <div class="order">
                <h2>${tr.translate("Order for")}: ${cart.getTotalPrice()}${tr.translate("*Curency*")}</h2>
                <ul class="steps menu horizontal">
                    <li class="toggle active" block="check-body">${tr.translate("STEP 1. Check.")}</li>
                    <li class="toggle" block="delivery-body">${tr.translate("STEP 2. Delivery.")}</li>
                    <li class="toggle" block="finish-body">${tr.translate("STEP 3. Finish.")}</li>
                </ul>
                <div class="content">
                    <div id="check-body" class="body">
                        <p class="step-info">
                            <span class="icon icon-info"></span> ${tr.translate("Check order info")}
                        </p>
                        
                        <label>${tr.translate("Name")}: </label>${user.firstName} ${user.lastName}<br>
                        <label>${tr.translate("Country")}: </label>${user.country}<br>
                        <label>${tr.translate("Address")}: </label><span id="address">${user.city},${user.address}</span><br>
                        
                        <hr>
                        
                        <label>${tr.translate("Credit card")}: </label>
                        ${user.creditCardType} <${user.creditCardNumber}> (${user.creditCardExp})<br>
                        
                        <hr>
                        
                        <label>${cart.getItemsCount()} ${tr.translate("items for ")}
                            <b>${cart.getTotalPrice()}</b>
                            ${tr.translate("*Curency*")} ${tr.translate("total")}.
                        </label>
                       
                    </div>
                    <div id="delivery-body" class="body">
                        <p class="step-info">
                            ${tr.translate("Choose delivery method")}
                        </p>
                        
                        <div id="delivery-main" class="pull-left">
                        <label id="delivery-currier-label">${tr.translate("Currier deliver")}: </label>
                        
                        <input id="deliver-currier" checked type="checkbox" name="currier"
                               onclick="Order.setDeliverType(1)">
                        
                        <br>
                        
                        <label id="delivery-shop-label">${tr.translate("Deliver to shop")}: </label>
                        <input id="deliver-shop" type="checkbox" name="shop"
                               onclick="Order.setDeliverType(2)">
                        </div>
                        
                        <div id="deliver-map" class="pull-right" style="display: none;">
                            <label>${tr.translate("Deliver to")}: </label>

                            <span id="deliver-shop-name"></span>
                            <hr>
                        </div>
                        
                    </div>
                    <div id="finish-body" class="body">
                        <p class="step-info">
                            ${tr.translate("Final approve")}
                        </p>
                        
                        <label>${tr.translate("Name")}: </label>${user.firstName} ${user.lastName}<br>
                        <label>${tr.translate("Country")}: </label>${user.country}<br>
                        <label>${tr.translate("Address")}: </label>${user.city},${user.address}<br>
                        
                        <label>${tr.translate("Credit card")}: </label>
                        ${user.creditCardType} <${user.creditCardNumber}> (${user.creditCardExp})<br>
                        
                        <hr> 
                        
                        <label>${tr.translate("Delivery method")}: </label><span id="deliver-method"></span><br>
                        <label>${tr.translate("Delivery address")}: </label><span id="deliver-address"></span><br>
                        <label>${tr.translate("Estimated delivery time")}: 48 ${tr.translate("hours")}</label> 
                        
                        <hr>
                        
                        <h2>${tr.translate("Total price")}: ${cart.getTotalPrice()}${tr.translate("*Curency*")}</h2>
                        
                    </div>
                        
                    <div id="order-controls" class="pull-left">
                        <button id="order-prev" class="btn default"
                                onclick="Order.prevStep();">
                            ${tr.translate("Previous step")}
                        </button> 
                        <button id="order-next" class="btn default"
                                onclick="Order.nextStep();">
                            ${tr.translate("Continue")}
                        </button>
                    </div>
                </div>
            </div>
        </section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
       
    </body>
</html>
