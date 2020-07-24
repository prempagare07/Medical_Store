<%--
  Created by IntelliJ IDEA.
  User: vanshikajain
  Date: 11/06/20
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Sign UP</title>
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/header.jsp"/>
<%--    <h3>Sign Up Now..</h3>--%>
<%--<div align="center" class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50"">--%>
<%--    <form action="SignIn" method="post">--%>
<%--        <div class="wrap-input100 ">--%>
<%--            Name: <input class="input100" type="text" name="name">--%>
<%--            <span class="focus-input100-1"></span>--%>
<%--            <span class="focus-input100-2"></span>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            Email: <input type="email" name="email"><br>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            Telephone No.: <input type="tel" name="tel"><br>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            Password: <input type="password" name="pass"><br>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <h8 style="color: red">${msg}</h8>--%>
<%--            <br>--%>
<%--        </div>--%>
<%--        <input type="submit" value="Register">--%>
<%--    </form>--%>
<%--</div>--%>

<div align="center" class="container">
    <div class="container-login100">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
            <form action="SignIn" method="post">
                <div>
                    <span class="login100-form-title p-b-33">
						Sign Up...
					</span>
                </div>
                <div>
                    <h8 style="color: red">${msg}</h8>
                </div>
                <div class="wrap-input100 validate-input">
                    <label> Name:</label>
                    <input type="text" name="name" placeholder="Name">
<%--                    <span class="focus-input100-1"></span>--%>
<%--                    <span class="focus-input100-2"></span>--%>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <label> Email:</label>
                    <input  type="email" name="email" placeholder="Email">
                    <span class="focus-input100-1"></span>
                    <span class="focus-input100-2"></span>
                </div>

                <div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
                    <label> Telephone:</label>
                    <input type="tel" name="tel" placeholder="Telephone">
                    <span class="focus-input100-1"></span>
                    <span class="focus-input100-2"></span>
                </div>

                <div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
                    <label> Password:</label>
                    <input  type="password" name="pass" placeholder="Password">
                    <span class="focus-input100-1"></span>
                    <span class="focus-input100-2"></span>
                </div>

                <div class="container-login100-form-btn m-t-20">
                    <button class="login100-form-btn">
                        Register Now
                    </button>
                </div>


            </form>
        </div>
    </div>
</div>
<c:remove var="msg" scope="session" />
<jsp:include page="/footer.jsp"/>

</body>
</html>
