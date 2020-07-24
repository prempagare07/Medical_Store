<%-- 
    Document   : search_results
    Created on : 12-Jun-2020, 3:20:12 PM
    Author     : SAMRUDDHI
--%>
<%@page import="java.util.List" %>
<%@page import="com.AppStore.domain.Application" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <title>Zenith</title>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class="container">
            <div class="row">
            <jsp:include page="/sidebar.jsp"/>
          <div class="col-lg-9">
              <div class="row">
                <c:set var="item" value='${requestScope["itemsList"]}'/>
                <c:set var="ott" value='${sessionScope["appLs"]}'/>
                <c:set var="flag" value='${0}'/>
                <c:forEach var="app" items="${item}">
                    <c:set var="check" value="${false}"/>
                    <c:forEach var="op" items="${ott}">
                        <c:if test="${op.getId()==app.getId()}">
                            <c:set var="check" value="${true}"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="flag" value="${1}"/>
                    <c:if test="${check == false}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <a href="individualPage.html?id=${app.getId()}"><img class="card-img-top"
                                                                                     src="${app.getLogo()}"
                                                                                     alt="${app.getName()}"></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="individualPage.html?id=${app.getId()}"><c:out
                                                value="${app.getName()}"/></a>
                                    </h4>
                                    <h5>Version = <c:out value="${app.getVersion()}"/></h5>
                                    <p class="card-text"><c:out value="${app.getDescription()}"/></p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">Rating: <c:out value="${app.getRating()}"/></small>
                                </div>
                            </div>
                        </div>
                    </c:if>      
                </c:forEach>
             </div>    
            <c:if test="${flag<1}">  
                <c:redirect url="/index.html"/>  
            </c:if>
            </div>
          </div>
        </div>
   <jsp:include page="/footer.jsp"/>
    </body>
</html>
