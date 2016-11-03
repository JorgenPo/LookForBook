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
        <link href="<c:url value='/styles/auth.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/product_list.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/footer.css'/>" rel="stylesheet">
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section><%--<c:import url="modules/special_offer.jsp" charEncoding="UTF-8" />--%></section>
        <section class="main-content">
            <%@ include file="/WEB-INF/modules/products_list.jspf" %>
        </section>
        <section><%--<c:import url="modules/certificates.jsp" charEncoding="UTF-8" />--%></section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
    </body>
</html>
