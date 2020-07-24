<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

    
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
<title>Bootstrap Sign up Form Horizontal</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style type="text/css">
	body {
		color: #999;
		background: #f3f3f3;
		font-family: 'Roboto', sans-serif;
	}
	.table-title {
		padding-bottom: 15px;
		background: #299be4;
		color: #fff;
		padding: 16px 30px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.table-title .btn {
		color: #566787;
		float: right;
		font-size: 13px;
		background: #fff;
		border: none;
		min-width: 50px;
		border-radius: 2px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-title .btn:hover, .table-title .btn:focus {
        color: #566787;
		background: #f2f2f2;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
    .form-control {
		border-color: #eee;
        min-height: 41px;
		box-shadow: none !important;
	}
    .form-control:focus {
		border-color: #5cd3b4;
	}
    .form-control, .btn {        
        border-radius: 3px;
    }
	.signup-form {
		width: 500px;
		margin: 0 auto;
		padding: 30px 0;
	}
    .signup-form h2 {
		color: #333;
        margin: 0 0 30px 0;
		display: inline-block;
		padding: 0 30px 10px 0;
		border-bottom: 3px solid #5cd3b4;
    }
    .signup-form form {
		color: #999;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #fff;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group {
		margin-bottom: 20px;
	}
	.signup-form label {
		font-weight: normal;
		font-size: 13px;
	}
    .signup-form input[type="checkbox"] {
		margin-top: 2px;
	}
    .signup-form .btn {        
        font-size: 16px;
        font-weight: bold;
		background: #5cd3b4;
		border: none;
		margin-top: 20px;
		min-width: 140px;
    }
	.signup-form .btn:hover, .signup-form .btn:focus {
		background: #41cba9;
        outline: none !important;
	}
    .signup-form a {
		color: #5cd3b4;
		text-decoration: underline;
	}
	.signup-form a:hover {
		text-decoration: none;
	}
    .signup-form form a {
		color: #5cd3b4;
		text-decoration: none;
	}	
	.signup-form form a:hover {
		text-decoration: underline;
	}
</style>
</head>
<body>

<div class="jumbotron">
<center>
	
        <h1 >App Management</h1>
        <h4>
            <a href="admin?func=new"><button class="btn-primary">Add New App</button></a>
            &nbsp;&nbsp;&nbsp;
            <a href="admin?func=list"><button class="btn-primary">List All Apps</button></a>
          </h4> 
        
    </center>
</div>

<div class="signup-form">

	<c:if test="${app != null}">
            <form action="admin?func=update" method="post" class = "form-horizontal">
        </c:if>
        <c:if test="${app == null}">
            <form action="admin?func=insert" method="post" class = "form-horizontal">
        </c:if>

		<div class="col-xs-8 col-xs-offset-4">
			<caption>
                <h2>
                    <c:if test="${app != null}">
                        Edit app
                    </c:if>
                    <c:if test="${app == null}">
                        Add New app
                    </c:if>
                </h2>
            </caption>
		</div>
		<c:if test="${app != null}">
                    <input type="hidden" name="id" value="<c:out value='${app.id}' />" />
        </c:if>
        
        		
        <div class="form-group">
			<label class="control-label col-xs-4">Id</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="id" required="required" value="<c:out value='${app.id}' />">
            </div>        	
        </div>
		<div class="form-group">
			<label class="control-label col-xs-4">Name</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="name" required="required" value="<c:out value='${app.name}' />">
            </div>        	
        </div>
		<div class="form-group">
			<label class="control-label col-xs-4">Description:</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="description" required="required"  value="<c:out value='${app.description}' />">
            </div>        	
        </div>
		<div class="form-group">
			<label class="control-label col-xs-4">category:</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="category" required="required"  value="<c:out value='${app.category}' />">
            </div>        	
        </div>
        <div class="form-group">
			<label class="control-label col-xs-4">Downloads:</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="downloads" required="required"  value="<c:out value='${app.numDownloads}' />">
            </div>        	
        </div>
        <div class="form-group">
			<label class="control-label col-xs-4">Rating:</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="rating" required="required"   value="<c:out value='${app.rating}' />">
            </div>        	
        </div>
        <div class="form-group">
			<label class="control-label col-xs-4">Logo :</label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="logo" required="required"  value="<c:out value='${app.logo}' />">
            </div>        	
        </div>
        <div class="form-group">
			<label class="control-label col-xs-4">Version : </label>
			<div class="col-xs-8">
                <input type="text" class="form-control" name="version" required="required" value="<c:out value='${app.version}' />">
            </div>        	
        </div> 
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				
				<input type="submit" class="btn btn-primary btn-lg" value ="Save">
			</div>  
		</div>		      
    </form>
</div>
</body>
</html>