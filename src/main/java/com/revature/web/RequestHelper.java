package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	
	// a logger
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	// an employeeService instance
	private static EmployeeService eserv = new EmployeeService(new EmployeeDAO());
	// an object mapper
	private static ObjectMapper om = new ObjectMapper(); // this is from Jackson Data Bind library
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
//		// extract the parameters from the HttpServerRequest (username & password)
//		BufferedReader reader = request.getReader(); // we have to extract username=someting&password=somethingelse
//		StringBuilder s = new StringBuilder();
//		
//		String line = reader.readLine();
//		
//		while(line != null) {
//			// append teh whole value to the stringbuilder object
//			s.append(line);
//			line = reader.readLine();			
//		}
//		
//		String body = s.toString();
//		String[] valuesSepByAmp = body.split("&"); // ["username=something", "password=something"]
//		
//		List<String> values = new ArrayList<String>();
//		
//		for(String pair : valuesSepByAmp) {
//			
//			values.add(pair.substring(pair.indexOf("=") + 1 )); // chopping up the string so only the value after = is left
//			// i.e. something instead of username=something
//			
//		}
//		
//		// capture the actual username and password
//		
//		String username = values.get(0);
//		String password = values.get(1);
		
		// extract the parameters from the HTTPServletRequest (username & password)
		// We're reading the username and password parameter values from the POST request
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		logger.info("User attempted to log in with username " + username);
		
		// call the confirmLogin() method with those values
		Employee e = eserv.confirmLogin(username, password);
		
		// IF the user is not null, save user to the session and print user info to the client with printwriter
		if (e != null) {
			
			// grab the session and add the user to it
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e); // binding hte retrieved user object to the session and giving it the key "the-user"
			
			// print the logged in user
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			// convert object with Jackson ObjectMapper and print it out
			out.println(om.writeValueAsString(e));
		} else {
			// if the returned object is null, return HTTP status called No Content Status
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			response.setStatus(204);
		}
			
		// call the service layer.. Which calls the dao layer
		
		// return some response (or redirect the user) if the employee object exists in the database
		
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1. set the content type of the response
		response.setContentType("text/html");
		
		// 2. call the findAll() method from the service layer and save it to a list
		List<Employee> allEmployees = eserv.findAll(); // This calls our DAO layer, which retrieves all objects from the DB
		
		// 3. Marshall the list of Java Objects to JSON (using Jackson as our Object Mapper)
		String jsonString = om.writeValueAsString(allEmployees);
		
		// 4. Call the PrintWRiter to write it out to the client (browser) in the response body
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
	}

	public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// if something goes wrong, redirect the user to a custom 404 page
		request.getRequestDispatcher("error.html").forward(request, response);
		/**
		 * forward() differs from sendRedirect() in that it DOES NOT produce a new request, but rather
		 * just forwards the same request to a new resource(maintaining the URL).
		 */
		
	}
	
	
}
