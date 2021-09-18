<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove Genre</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Remove Genre</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="removeGenre" method="post" modelAttribute="genre">
		<form:hidden path="genreId" />
		<table>
			<tr>
				<td><label for="genrename">Genre</label></td>
				<td><form:input path="genreName" id="genrename" /></td>
				<td><form:errors path="genreName" /></td>
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