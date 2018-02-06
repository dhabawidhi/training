package service;

import java.util.List;

import dto.EmployeeDto;
import dto.OrderDto;

public interface OrderSvc {
	public List<OrderDto> findAll();
	public List<OrderDto> findAllOrder();
	public void save(OrderDto orderDto);
	public void delete(OrderDto orderDto);
	public OrderDto findOne(String orderId);
}
