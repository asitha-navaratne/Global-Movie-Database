<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asith.gmdb.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Users</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

@SuppressWarnings("unchecked")
List<User> userList = (List<User>) request.getAttribute("users");
%>
<body>
	<a href="adminPage">Back</a>
	<h1>Users</h1>
	<table>
	<%
		for(User u : userList) {
	%>
		<tr>
			<td><%= u.getUserName() %></td>
			<td><em><%= u.getUserEmail() %></em></td>
		</tr>
	<%
		}
	%>
	</table>
</body>
</html>