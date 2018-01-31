package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;

import java.util.*;

import dao.EmployeeDao;
import entity.Employee;
import entity.EmployeePK;

public class tes {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		EmployeeDao employeeDao = ctx.getBean(EmployeeDao.class);
	 	List<Employee> employees= employeeDao.findAll();
	 	
	 	for(Employee employee: employees)
	 	{
	 		System.out.println("id\t:"+employee.getEmpId());
	 		System.out.println("name\t:"+employee.getEmpName());
	 		System.out.println("dob\t:"+employee.getEmpDOB());
	 		System.out.println("height\t:"+employee.getEmpHeight());
	 		System.out.println();
	 		
	 	}
	 	
	 	List<Object[]> names=employeeDao.getName();
	 	for(Object x:names)
	 	{	
	 		System.out.println(x);	
	 	}
	 	
	 	List<Object[]> names2=employeeDao.getNameFromNative();
	 	for(Object x:names2)
	 	{	
	 		System.out.println(x);	
	 	}
	 	
	 	Long countE= employeeDao.count();
	 	Double e2Double=Double.valueOf(countE);
	 	System.out.println(countE);
	 	System.out.println(e2Double);
	 	/*
	 	Employee e1= employeeDao.findOne("1");
	 	System.out.println(e1.getEmpName());
	 	*/
	 	/*
	 	Employee e2= new Employee();
	 	e2.setEmpId("4");
	 	e2.setEmpName("Irwan");
	 	e2.setEmpHeight(185);
	 	e2.setEmpDOB(new Date());
	 	employeeDao.save(e2);
	 	*/
	 	
//	 	Employee e3=new Employee();
//	 	e3.setEmpId("1");
	 	
	 	//buat delete semua
	 	/*
	 	employeeDao.deleteAllInBatch();
	 	*/
	 	
//	 	employeeDao.deleteInBatch("1");
	 	// find one
	 	
	 	EmployeePK e1= new EmployeePK();
	 	e1.setEmpId("1");
	 	Employee e3=employeeDao.findOne(e1);
	 	System.out.println(e3.getEmpName());
	 	
	 	
	 	Sort sort =new Sort(Sort.Direction.DESC,"empId");
	 	List<Employee> employees3=employeeDao.findAll(sort);
	 	for(Employee employee: employees3)
	 	{
	 		System.out.println("id\t:"+employee.getEmpId());
	 		System.out.println("name\t:"+employee.getEmpName());
	 		System.out.println("dob\t:"+employee.getEmpDOB());
	 		System.out.println("height\t:"+employee.getEmpHeight());
	 		System.out.println();
	 		
	 	}
	 	
	 	// this for delete
	 	/*
	 	EmployeePK eForDeleteEmployeePK=new EmployeePK();
	 	eForDeleteEmployeePK.setEmpId("4");
	 	employeeDao.delete(eForDeleteEmployeePK);
	 	*/
	}

}
