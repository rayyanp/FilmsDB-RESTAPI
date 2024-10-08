<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="f"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Films</title>
<link href="css/site.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style="margin-left:30px; margin-right:30px">
<center><h1>Films</h1></center>
<br>
<h2>Insert Films</h2>
	<form method="POST" action="./films">
		<input type="text" name="title" placeholder="Enter Title">
		<input type="text" name="director" placeholder="Enter Director">
		<input type="text" name="year" placeholder="Enter Year">
		<input type="text" name="stars" placeholder="Enter Stars">
		<input type="text" name="review" placeholder="Enter Review">
		<button type="submit" class="btn btn-success" value="submit">Insert</button>
	</form>
<br>
<h2>Table Of Films</h2>
	<table class="table">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Director</th>
			<th>Year</th>
			<th>Stars</th>
			<th>Review</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<f:forEach items="${films}" var="f">
			<tr>
				<td>${f.id}</td>
				<td>${f.title}</td>
				<td>${f.director}</td>
				<td>${f.year}</td>
				<td>${f.stars}</td>
				<td>${f.review}</td>
				<td><button type="submit" class="btn btn-success"><a href="./FilmsControllerAPI?id=${f.id}">Edit</a></button></td>
				<td><button type="submit" class="btn btn-danger"><a href="./FilmsControllerAPI?id=${f.id}">Delete</a></button></td>	
			</tr>
		</f:forEach>
	</table>
	<br>


</body>
</html>