package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	
	// a logger
	
	// an employeeService instance
	
	// an object mapper
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// extract the parameters from the HttpServerRequest (username & password)
		
		// call the service layer.. Which calls the dao layer
		
		// return some response (or redirect the user) if the employee object exists in the database
		
	}
	public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		
	}
	
}
