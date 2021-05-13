<%@ page import = "com.Project" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/project.js"></script>


<title>Project Details</title>
</head>
<body>



<h1>Project Management</h1>
<form id="formProject" name="formProject">
 Project name:
 <input id="ProjectName" name="ProjectName" type="text"
 class="form-control form-control-sm">
 <br> 
 Status:
 <input id="ProjectStatus" name="ProjectStatus" type="text"
 class="form-control form-control-sm">
 <br> 
 Start Date:
 <input id="ProjectStartDate" name="ProjectStartDate" type="text"
 class="form-control form-control-sm">
 <br>
 End Date:
 <input id="ProjectEndDate" name="ProjectEndDate" type="text"
 class="form-control form-control-sm">
 <br>
 Description:
 <input id="ProjectDescription" name="ProjectDescription" type="text"
 class="form-control form-control-sm">
 <br>
 Budget:
 <input id="ProjectBudget" name="ProjectBudget" type="text"
 class="form-control form-control-sm">
 <br>
 Price:
 <input id="ProjectPrice" name="ProjectPrice" type="text"
 class="form-control form-control-sm">
 <br>
 Researcher ID:
 <input id="ProjectResearcherID" name="ProjectResearcherID" type="text"
 class="form-control form-control-sm">
 <br>
 Sponsor ID:
 <input id="ProjectSponsorID" name="ProjectSponsorID" type="text"
 class="form-control form-control-sm">
 
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hideProjectIDSave"
 name="hideProjectIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>

<br>

<br><br>
	<div id="divProjectGrid">
	<%
		Project projectObj = new Project();
		out.print(projectObj.readProjects());
	%>
	
	 
	</div>

</body>
</html>