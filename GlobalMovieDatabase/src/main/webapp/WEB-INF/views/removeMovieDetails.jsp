<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove Movie Details</title>
</head>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
%>
<body>
	<h1>Choose Option</h1>
	<a href="removeGenre">Remove Genre</a>
	<br>
	<a href="removeDirector">Remove Director</a>
	<br>
	<a href="removeActor">Remove Actor</a>
	<br>
	<p>&nbsp;</p>
	<a href="adminPage">Back</a>
</body>
</html>