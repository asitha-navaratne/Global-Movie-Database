<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asith.gmdb.entity.Genre"%>
<%@ page import="com.asith.gmdb.entity.Director"%>
<%@ page import="com.asith.gmdb.entity.Actor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Movie</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

@SuppressWarnings("unchecked")
List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");

@SuppressWarnings("unchecked")
List<Director> directorList = (List<Director>) request.getAttribute("directorList");

@SuppressWarnings("unchecked")
List<Actor> actorList = (List<Actor>) request.getAttribute("actorList");
%>
<body>
	<h1>Add Movie</h1>
	<p>Enter movie details</p>
	<p>&nbsp;${errorMessage}&nbsp;</p>
	<form:form action="addMovie" method="post" modelAttribute="movie">
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
				<td><label for="runtimehours">Runtime Hours</label></td>
				<td><form:input path="movieRuntimeHours" id="runtimehours" /></td>
				<td><form:errors path="movieRuntimeHours" /></td>
			</tr>
			<tr>
				<td><label for="runtimeminutes">Runtime Minutes</label></td>
				<td><form:input path="movieRuntimeMinutes" id="runtimeminutes" /></td>
				<td><form:errors path="movieRuntimeMinutes" /></td>
			</tr>
			<tr>
				<td><label for="genres">Genres</label></td>
				<td>
					<select name="selectedGenres" id="genres" multiple="multiple" required>
						<option value="null" selected disabled>-- Select genre --</option>
						<%
							if(genreList != null) {
								for(Genre genre:genreList) {
						%>
									<option value="<%= genre.getGenreId() %>"><%= genre.getGenreName() %></option>
						<%	
								}
							}
						%>
					</select>
				</td>
				<td><form:errors path="genres" /></td>
			</tr>
			<tr>
				<td><label for="directors">Directors</label></td>
				<td>
					<select name="selectedDirectors" id="directors" multiple="multiple" required>
						<option value="null" selected disabled>-- Select director --</option>
						<%
							if(directorList != null) {
								for(Director director:directorList) {
						%>
									<option value="<%= director.getDirectorId() %>"><%= director.toString() %></option>
						<%
								}
							}
						%>
					</select>
				</td>
				<td><form:errors path="directors" /></td>
			</tr>
			<tr>
				<td><label for="actors">Actors</label></td>
				<td>
					<select name="selectedActors" id="actors" multiple="multiple" required>
						<option value="null" selected disabled>-- Select actor --</option>
						<%
							if(actorList != null) {
								for(Actor actor:actorList) {
						%>
									<option value="<%= actor.getActorId() %>"><%= actor.toString() %></option>
						<%
								}
							}
						%>
					</select>
					<td><form:errors path="actors" /></td>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Save"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
	<a href="adminPage">Back</a>
</body>
</html>