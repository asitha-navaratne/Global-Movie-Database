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
	<p>Enter new password</p>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="changePassword" method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<form:hidden path="userName" />
		<form:hidden path="userFirstName" />
		<form:hidden path="userLastName" />
		<form:hidden path="userEmail" />
		<form:hidden path="userPhone" />
		<table>
			<tr>
				<td><label for="password">Password</label></td>
				<td><form:password path="userPassword" id="password" /></td>
				<td><form:errors path="userPassword" /></td>
			</tr>
			<tr>
				<td><label for="cpassword">Confirm Password</label></td>
				<td><input type="password" name="cpassword" id="cpassword" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="profile">Back to profile</a>
</body>
</html>