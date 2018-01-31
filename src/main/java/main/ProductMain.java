package main;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmployeeDao;
import dao.ProductDao;
import entity.Employee;
import entity.Product;

public class ProductMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		ProductDao productDao = ctx.getBean(ProductDao.class);
	 	List<Product> products= productDao.findAll();
	 	
	 	for(Product x:products)
	 		
	 	{
	 		System.out.println("id\t:"+x.getProdId());
	 		System.out.println("name\t:"+x.getProdName());
	 		
	 	}
	 	
	 	List<Object[]> pNameList=productDao.getName();
	 	for(Object x:pNameList)
	 	{
	 		System.out.println(x);
	 		
	 	}
	 	
	 	List<Object[]> pNameList2=productDao.getNameNative();
	 	for(Object x:pNameList2)
	 	{
	 		System.out.println(x);
	 		
	 	}
	 	
	 	/*
	 	Product p1=new Product();
	 	p1.setProdId("3");
	 	p1.setProdName("Produk ketiga");
	 	p1.setProdPrice(234568);
	 	p1.setExpDate(new Date());
	 	productDao.save(p1);
	 	*/
	}

}
