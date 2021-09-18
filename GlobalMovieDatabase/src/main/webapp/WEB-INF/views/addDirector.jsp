<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Director</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Add Director</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="addDirector" method="post" modelAttribute="director">
		<form:hidden path="directorId" />
		<table>
			<tr>
				<td><label for="firstname">First Name</label></td>
				<td><form:input path="directorFirstName" id="firstname" /></td>
				<td><form:errors path="directorFirstName" /></td>
			</tr>
			<tr>
				<td><label for="lastname">Last Name</label></td>
				<td><form:input path="directorLastName" id="lastname" /></td>
				<td><form:errors path="directorLastName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Save"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="addMovieDetails">Back</a>
</body>
</html>