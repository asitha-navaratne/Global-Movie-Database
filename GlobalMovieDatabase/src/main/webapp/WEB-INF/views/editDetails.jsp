<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Details</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Edit Details</h1>
	<p>Enter new details</p>
	<form:form action="editDetails" method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<form:hidden path="userName" />
		<form:hidden path="userPassword" />
		<table>
			<tr>
				<td><label for="firstname">First Name</label></td>
				<td><form:input path="userFirstName" id="firstname" /></td>
				<td><form:errors path="userFirstName" /></td>
			</tr>
			<tr>
				<td><label for="lastname">Last Name</label></td>
				<td><form:input path="userLastName" id="lastname" /></td>
				<td><form:errors path="userLastName" /></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td><form:input path="userEmail" id="email" /></td>
				<td><form:errors path="userEmail" /></td>
			</tr>
			<tr>
				<td><label for="phone">Phone</label></td>
				<td><form:input path="userPhone" id="phone" /></td>
				<td><form:errors path="userPhone" /></td>
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