package main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CustomerDao;
import dao.ProductDao;
import entity.Customer;
import entity.Product;

public class CustomerName {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		CustomerDao customerDao = ctx.getBean(CustomerDao.class);
	 	
		/*
		List<Customer> customers= customerDao.findAll();
	 	for(Customer x:customers)
	 		
	 	{
	 		System.out.println("id\t:"+x.getCustId());
	 		System.out.println("name\t:"+x.getCustName());
	 		System.out.println("address\t:"+x.getCustAddress());
	 		
	 	}
	 	*/
	 	Customer c1=new Customer();
//	 	c1.setCustId(10);
	 	c1.setCustName("Aul");
	 	c1.setCustAddress("Cikupa");
	 	customerDao.save(c1);
	}
}
