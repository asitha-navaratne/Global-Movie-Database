<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="com.asith.gmdb.entity.User"%>
<%@ page import="com.asith.gmdb.entity.Movie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your movies</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

User user = (User) request.getAttribute("user");
%>
<body>
	<h1>Your movies</h1>
	<table>
		<%
			for(Movie movie : user.getMovies()) {
		%>
		<tr>
			<td><%= movie.toString() %></td>
			<td>
				<a href="rateMovie?movieId=<%= movie.getMovieId() %>">Rate Movie</a>&nbsp;
				<a href="deleteRating?movieId=<%= movie.getMovieId() %>">Delete Rating</a>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="profile">Back</a>
</body>
</html>