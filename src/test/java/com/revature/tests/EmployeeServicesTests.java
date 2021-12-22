package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServicesTests {

	private EmployeeDAO mockdao;
	private EmployeeService eserv;
	
	@Before
	public void setup() {
		
		mockdao = mock(EmployeeDAO.class);
		eserv = new EmployeeService(mockdao);
		
	}
	
	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}
	
	// Happy Path Scenario - test expected results when things go Right
	
	@Test
	public void testConfirmLogin_success() {
		// We create a fake DB of Employee objects
		Employee e1 = new Employee(3, "Scott", "Pilgrim", "spilgrim", "flowers");
		Employee e2 = new Employee(23, "Clint", "Barton", "hawkeye", "arrows");
		// add those employee objects to a list
		List<Employee> empStubs = new ArrayList<Employee>();
		empStubs.add(e1);
		empStubs.add(e2);
		
		// when the mockdao's findAll() method is called, it returns our dummy list (list of stubs)
		when(mockdao.findAll()).thenReturn(empStubs);
		/**
		 * Above, We intercept the data that would otherwise be returned from the database.
		 * 
		 * The EmployeeService confirmLogin method calls the DAO since it calls the DAO's findAll();
		 */
		
		// when we run the method, we should get e2
		Employee returnedEmployee = eserv.confirmLogin("hawkeye", "arrows");
		// use an assert equals on the eserv.confirmLogin that we return the right user
		assertEquals(e2, returnedEmployee);
		
	}
	
	@Test
	public void testConfirmLogin_fail() {
		// We create a fake DB of Employee objects
		Employee e1 = new Employee(3, "Scott", "Pilgrim", "spilgrim", "flowers");
		Employee e2 = new Employee(23, "Clint", "Barton", "hawkeye", "arrows");
		// add those employee objects to a list
		List<Employee> empStubs = new ArrayList<Employee>();
		empStubs.add(e1);
		empStubs.add(e2);
		
		// when the mockdao's findAll() method is called, it returns our dummy list (list of stubs)
		when(mockdao.findAll()).thenReturn(empStubs);
		/**
		 * Above, We intercept the data that would otherwise be returned from the database.
		 * 
		 * The EmployeeService confirmLogin method calls the DAO since it calls the DAO's findAll();
		 */
		
		// when we run try to test login 
		Employee returnedEmployee = eserv.confirmLogin("hawkeye", "arrow");
		// use an assert equals on the eserv.confirmLogin that we return the right user
		assertNull(returnedEmployee);
	}
	
}
