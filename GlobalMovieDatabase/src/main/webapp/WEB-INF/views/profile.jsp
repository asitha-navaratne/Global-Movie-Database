<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Welcome, ${user.getUserFirstName()}!</h1>
	<a href="purchaseMovie">Purchase Movie</a>
	<br>
	<a href="viewMovies">View Available Movies</a>
	<br>
	<a href="userMovies">Rate Your Movies</a>
	<br>
	<a href="editDetails">Edit Details</a>
	<br>
	<a href="changePassword">Change Password</a>
	<br>
	<a href="logout">Logout</a>
	<br>
</body>
</html>