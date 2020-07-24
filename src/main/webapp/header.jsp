
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index.html">Medical</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto" style="display:flex;justify-content: center;align-items: center">
                <li class="nav-item active">
                    <a class="nav-link" href="index.html">Home
                        <span class="sr-only">(current)</span>
                        <c:set var="check" scope="session"  value='${sessionScope["LoggedIn"]}'/>
                    </a>
                </li>
                <c:choose>
                    <c:when test='${check==false || check == null}'>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Login</a>
                        </li>
                    </c:when>
                    <c:otherwise>

                        <li class="nav-item">
                            <a class="nav-link" href="Logout">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="downloadsPage.jsp">My Cart</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                
             
            </ul>
        </div>
    </div>
</nav>
