<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
</head>
<body>
	<h1>GMDB</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="adminLogin" method="post" modelAttribute="admin">
		<form:hidden path="adminId" value="101" />
		<table>
			<tr>
				<td><label for="password">Password</label></td>
				<td><form:password path="adminPassword" id="password" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Login"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="login">Back</a>
</body>
</html>
