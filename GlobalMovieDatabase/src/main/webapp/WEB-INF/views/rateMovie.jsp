<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rate Movie</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Rate movie</h1>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="rateMovie" method="post" modelAttribute="rating">
		<form:hidden path="ratingId" />
		<table>
			<tr>
				<td><label for="userrating">Rating (out of 5)</label></td>
				<td>
					<form:select path="userRating" id="userrating">
						<form:option value="0" label="-- Select Rating --" disabled="true" />
						<form:option value="1" label="1" />
						<form:option value="2" label="2" />
						<form:option value="3" label="3" />
						<form:option value="4" label="4" />
						<form:option value="5" label="5" />
					</form:select>
				</td>
				<td><form:errors path="userRating" /></td>
			</tr>
			<tr>
				<td><label for="userdesc">Description</label></td>
				<td><form:textarea path="userDesc" id="userdesc" rows="5" cols="30" /></td>
				<td><form:errors path="userDesc" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="movieId" value="<%= request.getAttribute("movieId") %>" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="profile">Back</a>
</body>
</html>