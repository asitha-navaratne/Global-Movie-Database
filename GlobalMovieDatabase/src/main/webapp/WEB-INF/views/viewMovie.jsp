<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="com.asith.gmdb.entity.Movie"%>
<%@ page import="com.asith.gmdb.entity.Genre"%>
<%@ page import="com.asith.gmdb.entity.Director"%>
<%@ page import="com.asith.gmdb.entity.Actor"%>
<%@ page import="com.asith.gmdb.entity.Rating"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Movie</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

Movie movie = (Movie) request.getAttribute("movie");
%>
<body>
	<a href="purchaseMovie">Back</a>
	<h1><%= movie.toString() %></h1>
	<p>[
	<%
		for(Genre g : movie.getGenres()) {
			out.print(g.getGenreName() + " ");
		}
	%>
	]
	&nbsp;
	Rating: <em><%= movie.getMovieRating() %> stars</em></p>
	<br>
	<strong>Directors:</strong>
	<%
		for(Director d : movie.getDirectors()) {
	%>
			<p><%= d.toString() %></p>
	<%
		}
	%>
	<strong>Actors:</strong>
	<%
		for(Actor a : movie.getActors()) {
	%>
			<p><%= a.toString() %></p>
	<%
		}
	%>
	<br>
	<p><strong>Reviews:</strong></p>
	<%
		for(Rating r : movie.getRatings()) {
	%>
			<p>"<%= r.getUserDesc() %>"</p>
	<%
		}
	%>
</body>
</html>