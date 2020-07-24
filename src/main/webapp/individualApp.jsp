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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <c:set var="app" value='${requestScope["app"]}'/>
    <title><c:out value="${app.getName()}"/></title>
    
    <script>
        function updateStatus(){
            var request = new XMLHttpRequest();
            request.onreadystatechange = function(){
                if(this.readyState == 4){
                    document.getElementById("status").innerHTML = request.responseText;
                }
            };
            request.open("GET","subscribeHell?id=${id}",true);
            request.send();
        }
        window.setInterval(function(){
            updateStatus();
        },2000);
    </script>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="container">
<c:set var="ott" value='${sessionScope["appLs"]}'/>
<c:set var="flag" value="${false}"/>
<c:forEach var="op" items="${ott}">
    <c:if test="${op.getId()==app.getId()}">
        <c:set var="flag" value="${true}"/>
    </c:if>
</c:forEach>
    <div class="row">
        <jsp:include page="/sidebar.jsp"/>
        <c:set var="cat" value='${app.getCategory()}'/>
        <div class="col-lg-9">
            <c:set var="check" value='${sessionScope["LoggedIn"]}'/>
            <c:set var="msg" value='${(cat==AppCategory.INJECTION)?"NOT AVAILABLE":"BUY"}'/>
            <c:set var="status" value='${sessionScope["status"]}'/>
            <div class="card mt-4" style="display:flex;">
                <img class="card-img-top img-fluid" src='${app.getLogo()}' alt="${app.getName()}"
                     style="width:fit-content;height:auto">
                <c:choose>
                    <c:when test="${check == true && cat!=AppCategory.INJECTION && !flag}">
                        <span class='text-right'><a href="download.html?appId=${app.getId()}"><button class='btn btn-primary'><c:out value='${msg}'/></button></a></span>
                    </c:when>
                    <c:when test="${check == true && cat!=AppCategory.INJECTION && flag}">
                        <span class='text-right'><a href="download.html?appId=-1"><button class='btn btn-primary'>Go back to Downloads</button></a></span>
                    </c:when>
                    <c:when test='${check == true && cat == AppCategory.INJECTION && !flag}'>
                        <span class='text-right'><a href="download.html?appId=${app.getId()}"><button class='btn btn-primary'><c:out value='${msg}'/></button></a></span>
                    </c:when>
                    <c:when test="${check == true && cat==AppCategory.INJECTION && flag}">
                            <span class='text-left' id="status"></span>
                    </c:when>
                    <c:otherwise>
                        <span class='text-right'><a href="login.jsp"><button class='btn btn-primary'>Login to <c:out value='${msg}'/></button></a></span>
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h3 class="card-title"><c:out value="${app.getName()}"/></h3>
                    <p class="card-text"><c:out value="${app.getDescription()}"/></p>
                    <span class="card-text">Downloads : <c:out value="${app.getNumDownloads()}"/>+</span>
                    <span class="text-warning">Rating : <c:out value="${app.getRating()}"/></span>
                </div>
            </div>
            <!-- /.card -->
            <h3 class="card-title" style='margin-top:50px'>Customers also downloaded:</h3>
            <div class="row">

                <c:set var="id1" value="${app.getId()}"/>
                <c:set var="item" value='${requestScope["appList"]}'/>
                <c:forEach var="appli" items="${item}">
                    <c:set var="check" value="${false}"/>
                    <c:forEach var="op" items="${ott}">
                        <c:if test="${op.getId()==appli.getId()}">
                            <c:set var="check" value="${true}"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="id2" value="${appli.getId()}"/>
                    <c:if test="${id1!=id2 && !check}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <a href="individualPage.html?id=${appli.getId()}"><img class="card-img-top"
                                                                                       src="${appli.getLogo()}"
                                                                                       alt="${app.getName()}"></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="individualPage.html?id=${appli.getId()}"><c:out
                                                value="${appli.getName()}"/></a>
                                    </h4>
                                    <h5>Version = <c:out value="${appli.getVersion()}"/></h5>
                                    <p class="card-text"><c:out value="${appli.getDescription()}"/></p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">Rating: <c:out value="${appli.getRating()}"/></small>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/footer.jsp"/>
</body>
</html>
