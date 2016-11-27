<%-- 
    Document   : index
    Created on : 15.10.2016, 16:10:54
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
        <link href="<c:url value='/styles/cart.css'/>" rel="stylesheet">
                
        <script type="text/javascript" src="scripts/main.js"></script>
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section class="main-content">
            <div id="cart">
            <c:if test="${!cart.isEmpty()}">
                <table id="cart-table">
                    <caption>
                        Cart items
                    </caption>
                    
                    <colgroup>
                        <col id="item-col" />
                        <col id="description-col" />
                        <col id="price-col" />
                        <col id="count-col" />
                        <col id='delete-col' />
                        <col id="total-col" />
                    </colgroup>
                    
                    <thead>
                        <tr>
                            <th scope="col">Item</th>
                            <th scope="col">Item description</th>
                            <th scope="col">Item price</th>
                            <th scope="col">Item count</th>
                            <th scope="col"></th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    
                    <tbody id="cart-items">
                        <c:forEach var="product" items="${cart.getItems()}">
                            <c:set var="count" value="${cart.getItemCount(product)}" />
                            <%@ include file="/WEB-INF/modules/cart_item.jspf" %>
                        </c:forEach>
                    </tbody>
                    
                </table>
                <section id="order-block" align="right">
                    <h3>Order for: <b>${cart.getTotalPrice()}</b>
                        ${tr.translate("*Curency*")} total.
                    </h3>
                    <button class="btn default">Continue order!</button>
                </section>
            </div>
            </c:if>
            <p id="cart-empty" style="display: none;"><c:out value="Cart is empty"></c:out></p>
        </section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
    </body>
    
    <script>
        if (document.getElementById("cart-table") === null) {
            document.getElementById("cart-empty").style.display = "block";
        }
    </script>
</html>
