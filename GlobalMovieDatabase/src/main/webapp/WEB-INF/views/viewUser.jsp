<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="com.asith.gmdb.entity.User"%>
<%@ page import="com.asith.gmdb.entity.Movie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View User</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

User user = (User) request.getAttribute("user");
%>
<body>
	<a href="searchUser">Back</a>
	<h1>User <%= user.getUserId() %></h1>
	<p><strong>Username:</strong> <%= user.getUserName() %></p>
	<p><strong>First Name:</strong> <%= user.getUserFirstName() %></p>
	<p><strong>Last Name:</strong> <%= user.getUserLastName() %></p>
	<p><strong>Email:</strong> <%= user.getUserEmail() %></p>
	<p><strong>Phone:</strong> <%= user.getUserPhone() %></p>
	<br>
	<strong>Movies:</strong>
	<%
		for(Movie m : user.getMovies()) {
	%>
			<p><em><%= m.toString() %></em></p>
	<%
		}
	%>
</body>
</html>