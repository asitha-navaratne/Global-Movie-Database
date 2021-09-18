<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asith.gmdb.entity.Movie"%>
<%@ page import="com.asith.gmdb.entity.Genre"%>
<%@ page import="com.asith.gmdb.entity.Actor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase Movie</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

@SuppressWarnings("unchecked")
List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
%>
<body>
	<h1>Search Movie</h1>
	<p>Enter movie to be purchased</p>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="purchaseMovie" method="post" modelAttribute="movie">
		<form:hidden path="movieId" />
		<table>
			<tr>
				<td><label for="moviename">Movie Name</label></td>
				<td><form:input path="movieName" id="moviename" /></td>
				<td><form:errors path="movieName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	
	<%
		if(movieList != null) {
			for(Movie movie : movieList) {
	%>
				<strong><%= movie.toString() %></strong>&nbsp;<em><%= movie.getMovieRating() %> stars</em><br>
				<a href="addToUser?movieName=<%= movie.getMovieName() %>&movieYear=<%= movie.getMovieYear() %>">Buy</a>&nbsp;
				<a href="viewMovie?movieName=<%= movie.getMovieName() %>&movieYear=<%= movie.getMovieYear() %>">View</a>
				<p> [
	<%
				for(Genre genre : movie.getGenres()) {
	%>
					<%= genre.getGenreName() %>
	<%
				}
	%>
				] </p><p>
	<%
				for(Actor actor : movie.getActors()) {
	%>
					<%= actor.toString() %>&nbsp;
	<%
				}
	%>
				</p><br>
	<%
			}
		}
	%>
	<br>
	<a href="profile">Back</a>
</body>
</html>