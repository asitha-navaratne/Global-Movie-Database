<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asith.gmdb.entity.Movie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Movies</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

@SuppressWarnings("unchecked")
List<Movie> movieList = (List<Movie>) request.getAttribute("movies");
%>
<body>
	<h1>Available Movies</h1>
	<%
		for(Movie movie : movieList) {
	%>
			<p><%= movie.toString() %></p>
	<%
		}
	%>
	<a href="profile">Back</a>
</body>
</html>