<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management - GadgetBadget</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

</head>
<body>
	<div class="container">
		 <div class="row">
			 <div class="col-8">
			 <h1 class="m-3">Project details</h1>
			 <form id="formProject">
			 	<!--Project NAME -->
				 <div class="input-group input-group-sm mb-3">
					 <div class="input-group-prepend">
					 <span class="input-group-text" id="lblName">Project Name: </span>
					 </div>
				 <input type="text" id="txtName" name="txtName">
				 </div>
				 
				 <!-- Status -->
				 <div class="input-group input-group-sm mb-3">
				 <div class="input-group-prepend">
				 <span class="input-group-text" id="lblName">Status: </span>
				 </div>
				 &nbsp;&nbsp;On-going
				<input type="radio" id="rdoStatusOn-going" name="rdoStatus" value="On-going">
				 &nbsp;&nbsp;Completed
				<input type="radio" id="rdoStatusCompleted" name="rdoStatus" value="Completed">
				 </div>
				 
				 <!-- YEAR -->
				 <div class="input-group input-group-sm mb-3">
				 <div class="input-group-prepend">
				 <span class="input-group-text" id="lblName">Year: </span>
				 </div>
				 <select id="ddlYear" name="ddlYear">
				 <option value="0">--Select year--</option>
				 <option value="1">1st year</option>
				 <option value="2">2nd year</option>
				 <option value="3">3rd year</option>
				 <option value="4">4th year</option>
				 </select>
				 </div>
				 <div id="alertSuccess" class="alert alert-success"></div>
				 <div id="alertError" class="alert alert-danger"></div>
				<input type="button" id="btnSave" value="Save" class="btn btn-primary">
			 
			 </form>
			 </div>
	 	</div>
	
	 <br>
		 <div class="row">
			 <div class="col-12" id="colProjects">
			
			 </div>
		 </div>
	</div>
</body>

</html>