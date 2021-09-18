<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Welcome, Admin!</h1>
	<a href="addMovie">Add Movie</a>
	<br>
	<a href="viewUsers">View Users</a>
	<br>
	<a href="searchUser">Search User</a>
	<br>
	<a href="adminChangePassword">Change Password</a>
	<br>
	<a href="addMovieDetails">Add Actor/Director/Genre</a>
	<br>
	<a href="removeMovie">Remove Movie</a>
	<br>
	<a href="removeMovieDetails">Remove Actor/Director/Genre</a>
	<br>
	<a href="logout">Logout</a>
	<br>
</body>
</html>