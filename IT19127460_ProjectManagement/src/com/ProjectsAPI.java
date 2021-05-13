package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ProjectsAPI")
public class ProjectsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	private static Project projectObj; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				String output = projectObj.insertProject(request.getParameter("p_id"),
						request.getParameter("p_name"),
						request.getParameter("status"),
						request.getParameter("startDate"),
						request.getParameter("endDate"),
						request.getParameter("description"),
						request.getParameter("budget"),
						request.getParameter("price"),
						request.getParameter("researcher_id"),
						request.getParameter("sponsor_id"));
						response.getWriter().write(output);		
	}

	
	
	
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = projectObj.updateProject(paras.get("hideProjectIDSave").toString(),
				paras.get("p_name").toString(),
				paras.get("status").toString(),
				paras.get("startDate").toString(),
				paras.get("endDate").toString(),
				paras.get("description").toString(),
				paras.get("budget").toString(),
				paras.get("price").toString(),
				paras.get("researcher_id").toString(),
				paras.get("sponsor_id").toString());
				response.getWriter().write(output);
				
		
	}
	
	
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		 String output = projectObj.deleteProject(paras.get("p_id").toString());
		response.getWriter().write(output);
	}
	
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
		 Map<String, String> map = new HashMap<String, String>();
		 try
		 {
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
		
			String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
		 }
		 catch (Exception e)
		 {
			 System.out.print("Error!!");
		 }
		return map;
	}
}

