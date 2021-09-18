<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>
	<h1>GMDB</h1>
	<a href="adminLogin">Global Movie Database</a>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="login" method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<table>
			<tr>
				<td><label for="username">Username</label></td>
				<td><form:input path="userName" id="username" /></td>
				<td><form:errors path="userName" /></td>
			</tr>
			<tr>
				<td><label for="password">Password</label></td>
				<td><form:password path="userPassword" id="password" /></td>
				<td><form:errors path="userPassword" /></td>
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
	<a href="register">Not registered yet? Sign up</a>
</body>
</html>
