<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Change Password</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="adminChangePassword" method="post" modelAttribute="admin">
		<form:hidden path="adminId" value="101" />
		<table>
			<tr>
				<td><label for="password">Enter Password</label></td>
				<td><form:password path="adminPassword" id="password" /></td>
				<td><form:errors path="adminPassword" /></td>
			</tr>
			<tr>
				<td><label for="cpassword">Confirm Password</label></td>
				<td><input type="password" name="cpassword" id="cpassword"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Change"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="adminPage">Back</a>
</body>
</html>