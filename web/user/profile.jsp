<%-- 
    Document   : additem.jsp
    Created on : 30.10.2016, 16:57:41
    Author     : jorgen
--%>

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
        
        <script type="text/javascript" src="scripts/main.js"></script>
        
        <style>
            .user-info {
                margin-top: 10px;
                margin-left: 20px;
                width: 30%;
                text-align: left;
                line-height: 1.5em;
            }
            
            .user-main {
                clear: both;
                min-height: 200px;
                width: 60%;
            }
            
            .user-img {
                margin-right: 15px;
                min-height: 300px;
            }
            
            .user-main > div {
                margin-bottom: 20px;
                min-height: 100px;
            }
            .user-main > div > h2 {
                border-bottom: 2px solid #00A8A8;
            }
        </style>
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section class="main-content">
            <section class="user-profile">
                <div class="user-img pull-left">
                    <figure>
                        <figcaption>${user.getFullName()}</figcaption>
                        <img src="https://pp.vk.me/c631619/v631619942/254df/l9vYN8fiEEA.jpg"
                             alt="User photo">
                    </figure>
                    <a href="#"> ${tr.translate("Change photo")} </a>
                </div>
                
                <div class="user-info pull-left">
                    <label>${tr.translate("Name")}: </label>${user.firstName} ${user.lastName}<br>
                    <label>${tr.translate("Country")}: </label>${user.country}<br>
                    <label>${tr.translate("Address")}: </label>${user.city},${user.address}<br>
                    
                    <hr>
                    
                    <label>${tr.translate("Credit card")}: </label>
                    ${user.creditCardType} <${user.creditCardNumber}> (${user.creditCardExp})<br>
                    <label>${tr.translate("Invoices")}: </label><br>
                    
                    <label>${tr.translate("Default label")}: </label> ${defaultPage} <br>
                </div>
                
                <div class="user-main">
                    <hr>

                    <div class="user-orders">
                        <h2>${tr.translate("Last orders")}</h2><br>
                    </div>
                    
                    <div class="user-wishes">
                        <h2>${tr.translate("Wishes")}</h2><br>
                    </div>
                </div>
            </section>
        </section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
    </body>
</html>
