<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search User</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Search User</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="searchUser" method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<table>
			<tr>
				<td><label for="username">Enter username</label></td>
				<td><form:input path="userName" id="username" /></td>
				<td><form:errors path="userName" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Search"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="adminPage">Back</a>
</body>
</html>
