package main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailPK;
import entity.Product;

public class OrderMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		
		OrderDetailDao orderDetailDao = ctx.getBean(OrderDetailDao.class);
	 	List<Object[]> orders= orderDetailDao.findAllOrderDetail("o1");
	 	
	 	for(Object[] x:orders)
	 		
	 	{
	 		OrderDetail orderDetail=(OrderDetail) x[0];
	 		System.out.println(orderDetail.getOrderId());
	 		
	 		System.out.println(x[1]);
 		}
	
	 	/*
	 	OrderDetailDao orderDetailDao = ctx.getBean(OrderDetailDao.class);
	 	List<OrderDetail> orderDetails= orderDetailDao.findAll();
	 	
	 	for(OrderDetail x:orderDetails)
	 		
	 	{
	 		System.out.println("orderid\t:"+x.getOrderId());
	 		System.out.println("prodid\t:"+x.getProdId());
	 		System.out.println("prodprice\t:"+x.getProdPrice());
	 		System.out.println("prodqty\t:"+x.getProdQty());
	 		System.out.println("subtotal\t:"+x.getSubTotal());
	 		
	 	}
	 	*/
	 	/*
	 	OrderDetail orderDetail=new OrderDetail();
	 	orderDetail.setOrderId("2");
	 	orderDetail.setProdId("10");
	 	orderDetail.setProdPrice(50000);
	 	orderDetail.setProdQty(50);
	 	orderDetail.setSubTotal(2500000);
	 	
	 	orderDetailDao.save(orderDetail);
	 	
	 	*/
	 	/*
	 	OrderDetailPK orderDetailPK=new OrderDetailPK();
	 	orderDetailPK.setOrderId("2");
	 	orderDetailPK.setProdId("10");
	 	
	 	OrderDetail orderDetail2= orderDetailDao.findOne(orderDetailPK);
	 	System.out.println(orderDetail2.getOrderId());
	 	*/
	}
}
