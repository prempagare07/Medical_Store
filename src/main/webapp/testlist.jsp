<%-- 
    Document   : testlist
    Created on : 14-Jun-2020, 7:42:35 PM
    Author     : SAMRUDDHI
--%>

<%@page import="com.AppStore.servlets.adminSubscriptionControl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@page import="com.AppStore.domain.mysubscription" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
                <p>hello</p>
             <c:set var="item" value='${requestScope["appl"]}'/>
                  <c:forEach var="app" items="${item}">
	                <tr>
	                    
	                    <td><c:out value="${app.username}" /></td>
                            <td><c:out value="${app.id1}" /></td>
                            <td><c:out value="${app.appname}" /></td>
	                    <td><c:out value="${app.status1}" /></td>
	
	                    <td>
	                        <a class="settings" title="Settings" data-toggle="tooltip"  href="SubscriptionControl?func1=accept&id=<c:out value='${app.id1}'/>&uname=<c:out value='${app.username}'/>">accept</a>
	                                                    
	                    </td>
	                </tr>
            	</c:forEach>
            	
    </body>
</html>
