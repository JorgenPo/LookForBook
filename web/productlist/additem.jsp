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
        <link href="<c:url value='/styles/auth.css'/>" rel="stylesheet">
        <link href="<c:url value='/styles/footer.css'/>" rel="stylesheet">
        
        <script type="text/javascript" src="scripts/main.js"></script>
    </head>
    <body>
        <header><%@ include file="/WEB-INF/modules/header.jspf" %></header>
        <section class="main-content">
            ${form.render()}
<!--            <form method="post" action="add">
                <h2>${tr.translate("Submit book")}</h2>
                <p>${tr.translate(
                     "Please, enter book information and choose cover photo. Book will be added after moderation!"
                     )}</p>
                <label for="form-title">${tr.translate("Title")}</label>
                <input type="text" name="title" id="form-title" value="${book.title}"/>
                <label for="form-desc">${tr.translate("Description")}</label>
                <textarea type="text" name="desc" id="form-desc" >${book.title}</textarea>
                <label for="form-author">${tr.translate("Author")} </label>
                <input type="text" name="author" id="form-author" value="${book.title}"/>
                <label for="form-price">${tr.translate("Price")}</label>
                <input type="text" name="price" id="form-price" value="${book.title}"/>
                <label for="form-isbn">${tr.translate("ISBN")}</label>
                <input type="text" name="isbn" id="form-isbn" value="${book.title}"/>
                <label for="form-genre">${tr.translate("Genre")}</label>
                <select name="genre" id="form-genre">
                    <option>${tr.translate("Classic")}</option>
                </select>
                <label for="form-house">${tr.translate("Publish house")}</label>
                <input type="text" name="house" id="form-house" value="${book.title}"/>
                <label for="form-year">${tr.translate("Publish year")}</label>
                <input type="number" min="0" max="${YEAR}" name="year" 
                       value="<c:out value='${book.year}' default='${YEAR}'/>"
                       id="form-year" />
                <label for="form-lang">${tr.translate("Original language")}</label>
                <select name="lang" id="form-lang">
                    <option>English</option>
                    <option>Русский</option>
                    <option>Deautsch</option>
                </select>
                
                <input type="submit" class="btn default" value="${tr.translate("Add book")}"/>
            </form>-->
        </section>
        <footer><%@ include file="/WEB-INF/modules/footer.jspf" %></footer>
    </body>
</html>
