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
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section class="main-content">
            <c:if test="${error != null}">
                <p class="error"><span class="icon icon-error"> Error: ${error}</span></p> 
            </c:if>
            ${form.render()}
        </section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
    </body>
</html>
