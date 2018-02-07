package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.OrderDao;
import dao.OrderDetailDao;
import dto.OrderDetailDto;
import dto.OrderDto;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailPK;
import entity.OrderPK;
import service.OrderDetailSvc;

@Service("orderDetailSvc") // copy nama interface dan buat jadi huruf kecil nama servicenya
@Transactional
public class OrderDetailSvcImpl implements OrderDetailSvc {
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	
	@Override
	public List<OrderDetailDto> findAll() {
		List<OrderDetailDto> orderDetailDtos= new ArrayList<OrderDetailDto>();
		Sort sort =new Sort(Sort.Direction.ASC,"orderId");
		List<OrderDetail> orderDetails=orderDetailDao.findAll(sort);
		for(OrderDetail orderDetail:orderDetails)
		{
			OrderDetailDto orderDetailDto=new OrderDetailDto();
			orderDetailDto.setOrderId(orderDetail.getOrderId());
			orderDetailDto.setProdId(orderDetail.getProdId());
			orderDetailDto.setProdPrice(orderDetail.getProdPrice());
			orderDetailDto.setProdQty(orderDetail.getProdQty());
			orderDetailDto.setSubTotal(orderDetail.getSubTotal());
			orderDetailDtos.add(orderDetailDto);
		}
		
		return orderDetailDtos;
	}

	@Override
	public void save(OrderDetailDto orderDetailDto) {
		
		System.out.println("orderId\t:"+orderDetailDto.getOrderId());
		System.out.println("prodId\t:"+orderDetailDto.getProdId());
		System.out.println("prodPrice\t"+orderDetailDto.getProdPrice());
		System.out.println("prodQty\t:"+orderDetailDto.getProdQty());
		System.out.println("subTotal\t:"+orderDetailDto.getSubTotal());
		OrderDetail orderDetail=new OrderDetail();
		
		
		orderDetail.setOrderId(orderDetailDto.getOrderId());
		orderDetail.setProdId(orderDetailDto.getProdId());
		orderDetail.setProdPrice(orderDetailDto.getProdPrice());
		orderDetail.setProdQty(orderDetailDto.getProdQty());
		orderDetail.setSubTotal(orderDetailDto.getSubTotal());
		
		orderDetailDao.save(orderDetail);
		

	}

	@Override
	public void delete(OrderDetailDto orderDetailDto) {
		OrderDetailPK orderDetailPK=new OrderDetailPK();
		orderDetailPK.setOrderId(orderDetailDto.getOrderId());
		orderDetailPK.setProdId(orderDetailDto.getProdId());
		orderDetailDao.delete(orderDetailPK);
	

	}

	@Override
	public OrderDetailDto findOne(String orderId, String prodId) {
		OrderDetailDto orderDetailDto=new OrderDetailDto();
		OrderDetailPK orderDetailPK=new OrderDetailPK();
		orderDetailPK.setOrderId(orderId);
		OrderDetail orderDetail=orderDetailDao.findOne(orderDetailPK);
		if(orderDetail!=null)
		{
			orderDetailDto.setOrderId(orderDetail.getOrderId());
			orderDetailDto.setProdId(orderDetail.getOrderId());
			orderDetailDto.setProdPrice(orderDetail.getProdPrice());
			orderDetailDto.setProdQty(orderDetail.getProdQty());
			orderDetailDto.setSubTotal(orderDetail.getSubTotal());
		}
		return orderDetailDto;
	}
	
}
