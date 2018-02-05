package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import dto.CustomerDto;
import dto.EmployeeDto;
import entity.Customer;
import entity.CustomerPK;
import entity.Employee;
import entity.EmployeePK;
import service.CustomerSvc;

@Service("customerSvc") // copy nama interface dan buat jadi huruf kecil nama servicenya
@Transactional
public class CustomerSvcImpl implements CustomerSvc {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<CustomerDto> findAll() {
		// TODO Auto-generated method stub
		List<CustomerDto> customerDtos= new ArrayList<CustomerDto>();
		Sort sort =new Sort(Sort.Direction.ASC,"custId");
		List<Customer> customers=customerDao.findAll(sort);
		for(Customer customer:customers)
		{
			CustomerDto customerDto=new CustomerDto();
			customerDto.setCustAddress(customer.getCustAddress());
			customerDto.setCustId(customer.getCustId());
			customerDto.setCustName(customer.getCustName());
			customerDtos.add(customerDto);
			
		}
		return customerDtos;
	}

	@Override
	public void save(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		customer.setCustAddress(customerDto.getCustAddress());
		customer.setCustId(customerDto.getCustId());
		customer.setCustName(customerDto.getCustName());
		customerDao.save(customer);
	}

	@Override
	public void delete(CustomerDto customerDto) {
		CustomerPK customerPK=new CustomerPK();
		customerPK.setCustId(customerDto.getCustId());
		customerDao.delete(customerDto.getCustId());
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerDto findOne(int custId) {
		CustomerDto customerDto=new CustomerDto();
		CustomerPK customerPK=new CustomerPK();
		customerPK.setCustId(custId);
		Customer customer=customerDao.findOne(custId);
		if(customer!=null)
		{
			customerDto.setCustAddress(customer.getCustAddress());
			customerDto.setCustId(customer.getCustId());
			customerDto.setCustName(customer.getCustName());
		}
		return customerDto;
		
	}

}
