<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove Movie</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Remove Movie</h1>
	<p>Enter movie to be deleted</p>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="removeMovie" method="post" modelAttribute="movie">
		<form:hidden path="movieId" />
		<table>
			<tr>
				<td><label for="moviename">Movie Name</label></td>
				<td><form:input path="movieName" id="moviename" /></td>
				<td><form:errors path="movieName" /></td>
			</tr>
			<tr>
				<td><label for="movieyear">Movie Year</label></td>
				<td><form:input path="movieYear" id="movieyear" /></td>
				<td><form:errors path="movieYear" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Remove"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="adminPage">Back</a>
</body>
</html>