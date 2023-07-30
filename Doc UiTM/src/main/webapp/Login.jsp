<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
  
  %> 
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
	<script src="https://kit.fontawesome.com/9ed6a12a9d.js"></script>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script> 
		function validate() { 
		     var userid = document.form.userid.value; 
		     var password = document.form.password.value;
		 
		     if (userid==null) { 
			     alert("User ID cannot be blank"); 
			     return false; 
		     }
		     else if(password==null) { 
			     alert("Password cannot be blank"); 
			     return false; 
		     } 
		}
</script> 
</head>
<body background="image/medBG-green.png">

	<header>
		<img class="logo" src="image/smallerlogof.png" alt="logo">
	</header>
	
	<div class="loginbox">
	<img src="image/avatar2.jpg" class="avatar">
		<h1>LOGIN HERE</h1>
		<form action="LoginController" method="post" onsubmit="return validate()">
		<p>Username</p>
		<input type="text" name="userid" placeholder="Enter ID">
		<p>Password</p>
		<input type="password" name="password" placeholder="Enter IC Number">
		<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></span>
		<br><input type="submit" value="Login">
		</form>
	</div>
</body>
</html>