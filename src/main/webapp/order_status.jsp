<%--
Document   : index
Created on : Jun 10, 2020, 10:47:02 AM
Author     : HP
--%>

<%@page import="java.util.List" %>
<%@page import="com.AppStore.domain.Application" %>
<%@page import="com.AppStore.domain.AppCategory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/shop-homepage.css" rel="stylesheet">
        <c:set var="app" value='${requestScope["app"]}'/>
        <c:set var="userName" value='${sessionScope["uname"]}'/>
        <title><c:out value="${app.getName()}"/></title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="container">
            <div class="row">
                <jsp:include page="/sidebar.jsp"/>
                <div class="col-lg-9">
                    <h1 id="username_holder" class="card-title">Downloads For ${userName}</h1>
                    <p id="app_status_body"></p>
                </div>
            </div>
        </div>
        <jsp:include page="/footer.jsp"/>
    <script src="order_status.js"></script>
    </body>
</html>
