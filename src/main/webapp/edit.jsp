<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit film</title>
<link href="css/site.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style="margin-left:30px; margin-right:30px">
<div class="col-md-8 m-auto block" id="main_content">


<center><h1>Edit Film</h1></center>



<form method="POST" action="./FilmsControllerAPI">
      <div class="form-group form-group-position">
		<input type="hidden" name="id"  value="${film.id}"> 
		</div>
		      <div class="form-group form-group-position">
		<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${film.title}">
		      </div>
		      <div class="form-group form-group-position">
		<input type="text" name="year" class="form-control" placeholder="Enter Title" value="${film.year}">
		</div>
		      <div class="form-group form-group-position">
		<input type="text" name="director" class="form-control" placeholder="Enter Title" value="${film.director}">
		</div>
		      <div class="form-group form-group-position">
		<input type="text" name="stars" class="form-control" placeholder="Enter Title" value="${film.stars}">
		</div>
		      <div class="form-group form-group-position">
		<input type="text" name="review" class="form-control" placeholder="Enter Title" value="${film.review}">      
		</div>
		<button type="submit" class="btn btn-success" value="submit">Update</button>
	</form>

</div>
</body>
</html>