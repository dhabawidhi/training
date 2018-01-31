package service;

import java.util.List;

import dto.CustomerDto;

public interface CustomerSvc {
	public List<CustomerDto> findAll();
	public void save(CustomerDto customerDto);
	public void delete(CustomerDto customerDto);
	public CustomerDto findOne(int custId);
}
