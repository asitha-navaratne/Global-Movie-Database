<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove Actor</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Remove Actor</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="removeActor" method="post" modelAttribute="actor">
		<form:hidden path="actorId" />
		<table>
			<tr>
				<td><label for="firstname">First Name</label></td>
				<td><form:input path="actorFirstName" id="firstname" /></td>
				<td><form:errors path="actorFirstName" /></td>
			</tr>
			<tr>
				<td><label for="lastname">Last Name</label></td>
				<td><form:input path="actorLastName" id="lastname" /></td>
				<td><form:errors path="actorLastName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Remove"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="removeMovieDetails">Back</a>
</body>
</html>