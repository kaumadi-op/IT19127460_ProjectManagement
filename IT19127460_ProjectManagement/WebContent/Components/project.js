
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	}
	
	// If valid------------------------
	var type = ($("#hideProjectIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "ProjectsAPI",
		type : type,
		data : $("#formProject").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onProjectSaveComplete(response.responseText, status);
		}
	});
});




// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hideProjectSave").val($(this).closest("tr").find('#hideProjectIDUpdate').val());
	 $("#ProjectName").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#ProjectStatus").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#ProjectStartDate").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#ProjectEndDate").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#ProjectDescription").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#ProjectBudget").val($(this).closest("tr").find('td:eq(5)').text());
	 $("#ProjectPrice").val($(this).closest("tr").find('td:eq(6)').text());
	 $("#ProjectResearcherID").val($(this).closest("tr").find('td:eq(7)').text());
	 $("#ProjectSponsorID").val($(this).closest("tr").find('td:eq(8)').text());
});


// CLIENT-MODEL================================================================
function validateProjectForm()
{
	// Name-----------------------------------
	if ($("#ProjectName").val().trim() == "")
	 {
	 	return "Insert Project Name!!";
	 }
	 
	// Status----------------------------------
	if ($("#ProjectStatus").val().trim() == "")
	 {
	 	return "Insert Project Status!!!";
	 } 
	 
	// StartDate----------------------------------
	if ($("#ProjectStartDate").val().trim() == "")
	 {
	 	return "Insert Project Start Date!!!";
	 } 
	
	// EndDate-------------------------------
	if ($("#ProjectEndDate").val().trim() == "")
	 {
	 	return "Insert Project End Date!!";
	 }
	 
	 // DESCRIPTION------------------------
	if ($("#ProjectDescription").val().trim() == "")
	 {
	 	return "Insert Project Description!!!";
	 }
	
	 // Budget------------------------
	if ($("#ProjectBudget").val().trim() == "")
	 {
	 	return "Insert Project Budget!!!";
	 }
	// is numerical value
	var tmpBudget = $("#ProjectBudget").val().trim();
	if (!$.isNumeric(tmpBudget))
	 {
	 	return "Insert a numerical value for Project Budget!!!";
	 }
	 
	// convert to decimal price
	 $("#ProjectBudget").val(parseFloat(tmpBudget).toFixed(2));
	 
	 
	 
	 // Price------------------------
	if ($("#ProjectPrice").val().trim() == "")
	 {
	 	return "Insert Project Price!!!";
	 }
	// is numerical value
	var tmpPrice = $("#ProjectPrice").val().trim();
	if (!$.isNumeric(tmpPrice))
	 {
	 	return "Insert a numerical value for Project Price!!!";
	 }
	 
	// convert to decimal price
	 $("#ProjectPrice").val(parseFloat(tmpPrice).toFixed(2));
	 
	 
	 // ResearcherID-----------------------------------
	if ($("#ProjectResearcherID").val().trim() == "")
	 {
	 	return "Insert Project Researcher ID!!";
	 }
	 
	 
	 // SponsorID-----------------------------------
	if ($("#ProjectSponsorID").val().trim() == "")
	 {
	 	return "Insert Project SponsorID!!";
	 }
	 
	 return true;
	
}

function onProjectSaveComplete(response, status)
{
	if (status == "success")
	{
	 var resultSet = JSON.parse(response);
	 if (resultSet.status.trim() == "success")
	 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 $("#divProjectsGrid").html(resultSet.data);
	 } 
	 else if (resultSet.status.trim() == "error")
	 {
		 $("#alertError").text(resultSet.data);
		 $("#alertError").show();
	 }
	} 
	else if (status == "error")
	{
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
	} 
	else
	{
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	}
	
	 
	 $("#hideProjectIDSave").val("");
	 $("#formProject")[0].reset();
}
	
	
	
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
			 url : "ProjectsAPI",
			 type : "DELETE",
			 data : "p_id=" + $(this).data("p_id"),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 	onProjectDeleteComplete(response.responseText, status);
			}
	});
});





function onProjectDeleteComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divProjectGrid").html(resultSet.data);
		 } 
		 else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	} 
	else if (status == "error")
	{
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	} 
	else
	{
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
	}
}