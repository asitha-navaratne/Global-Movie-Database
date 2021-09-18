<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<body>
	<h1>Sign up</h1>
	<p>Enter your details</p>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="register" method="post" modelAttribute="user">
		<form:hidden path="userId" />
		<table>
			<tr>
				<td><label for="username">Username</label></td>
				<td><form:input path="userName" id="username" /></td>
				<td><form:errors path="userName" /></td>
			</tr>
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
	<a href="login">Back to login page</a>
</body>
</html>