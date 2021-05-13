package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	
	//Database Connection
	public Connection connect()
	{
		 Connection con = null;
	
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadget_badget","root", "");
			 //For testing
			 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 System.out.print("Connection Unsuccess !!");
		 }
	
		 return con;
	}
	
	
	
	//Insert Project Details
	public String insertProject(String p_id, String p_name, String status, String startDate, String endDate, String description, String budget, String price, String researcher_id, String sponsor_id)
	{
		String output = "";
		
		try
		{
			//DB connection
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database";
			 }
			 
				 // create a prepared statement
				 String query = "INSERT INTO `projects`( `p_name`, `status`, `startDate`, `endDate`, `description`, `budget`, `price`, `researcher_id`, `sponsor_id`) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
			 
				// binding values
				st.setInt(1, 0);
				st.setString(2, p_name);
				st.setString(3, status);
				st.setString(4, startDate);
				st.setString(5, endDate);
				st.setString(6, description);
				st.setFloat(7, Float.parseFloat(budget));
				st.setFloat(8,Float.parseFloat(price));
				st.setInt(9, Integer.parseInt(researcher_id));
				st.setInt(10, Integer.parseInt(sponsor_id));
				
				 //execute the statement
				 st.execute();
				 con.close();
				 String newProjects = readProjects();
				 output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}";
		}
		
		catch (Exception e)
		{
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the projects.\"}";
		 System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	//Read project table details
	public String readProjects()
	{
		String output = "";
		
		try
		{
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for reading.";
		 }
		 
		// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Project ID</th><th>Project Name</th><th>Status</th><th>Start Date</th><th>End Date</th><th>Description</th><th>Budget</th><th>Price</th><th>Researcher ID</th><th>Sponsor ID</th>" +
					"<th>Update</th><th>Remove</th></tr>";
					String query = "SELECT * FROM `projects`";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String p_id = Integer.toString(rs.getInt("p_id"));
						String p_name = rs.getString("p_name");
						String status = rs.getString("status");
						String startDate = rs.getString("startDate");
						String endDate = rs.getString("endDate");
						String description = rs.getString("description");
						String budget = Float.toString(rs.getFloat("budget"));
						String price = Float.toString(rs.getFloat("price"));
						String researcher_id = Integer.toString(rs.getInt("researcher_id"));
						String sponsor_id = Integer.toString(rs.getInt("sponsor_id"));
						
						// Add into the html table
						output += "<tr><td>" + p_id + "</td>";
						output += "<td>" + p_name + "</td>";
						output += "<td>" + status + "</td>";
						output += "<td>" + startDate + "</td>";
						output += "<td>" + endDate + "</td>";
						output += "<td>" + description + "</td>";
						output += "<td>" + budget + "</td>";
						output += "<td>" + price + "</td>";
						output += "<td>" + researcher_id + "</td>";
						output += "<td>" + sponsor_id + "</td>";
						
						// buttons
						output += "<td><form method='post' action='index.jsp'><td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='p_id' type='hidden' value='" + p_id + "'>" + "</form></td></tr>";
					}
					
					//close the db connection
					con.close();
					
					
					// Complete the html table
					output += "</table>";
		}
		
		catch (Exception e)
		{
		 output = "Error while reading the projects!!";
		 System.err.println(e.getMessage());
		}
		
		return output;
	}

	
	
	
	//update project details 
	public String updateProject(String p_id, String p_name, String status, String startDate, String endDate, String description, String budget, String price, String researcher_id, String sponsor_id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database";
			}
			
			// create a prepared statement
			String updateProject = "UPDATE `projects` SET `p_id`='"+p_id+"',p_name='"+p_name+"',status='"+status+"',startDate='"+startDate+"',endDate='"+endDate+"',description='"+description+"',budget='"+budget+"',price='"+price+"',researcher_id='"+researcher_id+"',sponsor_id='"+sponsor_id+"' WHERE p_id='"+p_id+"'";
			PreparedStatement st = con.prepareStatement(updateProject);
	
			//execute the statement
			st.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//Delete project details
	public String deleteProject(String p_id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String deleteProject = "DELETE FROM projects WHERE p_id = '"+p_id+"'";
			PreparedStatement ps = con.prepareStatement(deleteProject);
			
			// binding values
			ps.setInt(1, Integer.parseInt(p_id));
			// execute the statement
			ps.execute();
			con.close();
			String newProjects = readProjects();
			output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}";
		}
		catch (Exception e)
		{
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the project.\"}";
		 System.err.println(e.getMessage());
		}
		return output;
	}



	


}
