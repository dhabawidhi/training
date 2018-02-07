package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.EmployeeDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.OrderDto;
import dto.OrderDto;
import entity.Employee;
import entity.EmployeePK;
import entity.Order;
import entity.OrderDetail;
import entity.OrderPK;
import entity.Product;
import entity.ProductPK;
import service.OrderSvc;

@Service("orderSvc") // copy nama interface dan buat jadi huruf kecil nama servicenya
@Transactional
public class OrderSvcImpl implements OrderSvc {
	
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<OrderDto> findAll() {
		List<OrderDto> orderDtos= new ArrayList<OrderDto>();
		Sort sort =new Sort(Sort.Direction.ASC,"orderId");
		List<Order> orders=orderDao.findAll(sort);
		for(Order order:orders)
		{
			OrderDto orderDto=new OrderDto();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setCustId(order.getCustId());;
			orderDto.setTotal(order.getTotal());
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}

	@Override
	public void save(OrderDto orderDto) {
		// TODO Auto-generated method stub
		Order order=new Order();
		order.setOrderId(orderDto.getOrderId());
		order.setCustId(orderDto.getCustId());;
		order.setTotal(orderDto.getTotal());
		
		orderDao.save(order);
		/*
		OrderDetailDto orderDetailDto=(OrderDetailDto) orderDto.getOrderDetailDtos();
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderId(orderDetailDto.getOrderId());
		orderDetail.setProdId(orderDetailDto.getProdId());
		orderDetail.setProdPrice(orderDetail.getProdPrice());
		orderDetail.setProdQty(orderDetail.getProdQty());
		orderDetail.setSubTotal(orderDetail.getSubTotal());
		*/
		for(OrderDetailDto orderDetailDto:orderDto.getOrderDetailDtos())
		{
			OrderDetail orderDetail=new OrderDetail();
			System.out.println("orderdto\t"+orderDetailDto.getOrderId());
			System.out.println("proddto\t"+orderDetailDto.getProdId());
			System.out.println("priceto\t"+orderDetailDto.getProdPrice());
			System.out.println("prodqtydto\t"+orderDetailDto.getProdQty());
			System.out.println("subtotaldto\t"+orderDetailDto.getSubTotal());
			
			orderDetail.setOrderId(orderDetailDto.getOrderId());
			orderDetail.setProdId(orderDetailDto.getProdId());
			orderDetail.setProdPrice(orderDetailDto.getProdPrice());
			orderDetail.setProdQty(orderDetailDto.getProdQty());
			orderDetail.setSubTotal(orderDetailDto.getSubTotal());
			orderDetailDao.save(orderDetail);
		}
		
	}

	@Override
	public void delete(OrderDto orderDto) {
		// TODO Auto-generated method stub
		OrderPK orderPK=new OrderPK();
		orderPK.setOrderId(orderDto.getOrderId());
		orderDetailDao.deleteOrder(orderDto.getOrderId());
		orderDao.delete(orderPK);
	}

	@Override
	public OrderDto findOne(String orderId) {
		OrderDto orderDto=new OrderDto();
		OrderPK orderPK=new OrderPK();
		orderPK.setOrderId(orderId);;
		Order order=orderDao.findOne(orderPK);
		if(order!=null)
		{
			orderDto.setOrderId(order.getOrderId());
			orderDto.setCustId(order.getCustId());;
			orderDto.setTotal(order.getTotal());
		}
		return orderDto;
	}

	@Override
	public List<OrderDto> findAllOrder() {
		List<OrderDto> orderDtos= new ArrayList<OrderDto>();
		
		List<Object[]> orders=orderDao.findAllOrder();
		for(Object[] order:orders)
		{
			OrderDto orderDto=new OrderDto();
			orderDto.setOrderId((String) order[0]);
			orderDto.setCustId((int) order[1]);;
//			orderDto.setTotal((int) order[2]);
			orderDto.setCustName((String) order[3]);
			
			List<OrderDetailDto> orderDetailDtos=new ArrayList<OrderDetailDto>();
			List<Object[]> orderDetailList=orderDetailDao.findAllOrderDetail((String) order[0]);
			int total=0;
			for(Object[] od:orderDetailList)
			{
				OrderDetailDto orderDetailDto=new OrderDetailDto();
				OrderDetail orderDetail=(OrderDetail) od[0];
				orderDetailDto.setOrderId(orderDetail.getOrderId());
				orderDetailDto.setProdId(orderDetail.getProdId());
				orderDetailDto.setProdPrice(orderDetail.getProdPrice());
				orderDetailDto.setProdQty(orderDetail.getProdQty());
				orderDetailDto.setSubTotal(orderDetail.getSubTotal());
				total=total+orderDetail.getProdPrice()*orderDetail.getProdQty();
				orderDetailDto.setProdName((String) od[1]);
				orderDetailDtos.add(orderDetailDto);
			}
			orderDto.setTotal(total);
			orderDto.setOrderDetailDtos(orderDetailDtos);
			
	
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}

}
