package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAO {

	public int insert(Employee e) {
		
		// Grab the session object
		Session ses = HibernateUtil.getSession();
		
		// Begin a tx
		Transaction tx = ses.beginTransaction();
		
		// Capture the PK returned from save()
		int pk = (int) ses.save(e);
		
		// commit the tx
		return pk;
		
		
	}
	
	public List<Employee> findAll() {
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make a HQL statement to return all records from the Employee table
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		// return that list
		return emps;
	}
	
	
	public boolean update(Employee e) {
		return false;
	}
	
	public boolean delete(Employee e) {
		return false;
	}
	
}
