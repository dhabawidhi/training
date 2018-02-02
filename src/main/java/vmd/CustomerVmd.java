package vmd;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import dto.CustomerDto;
import dto.ProductDto;
import service.CustomerSvc;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVmd {
	@WireVariable
	private CustomerSvc customerSvc;
	
	private List<CustomerDto> customerDtos;
	private CustomerDto customerDto;
	public CustomerSvc getCustomerSvc() {
		return customerSvc;
	}
	public void setCustomerSvc(CustomerSvc customerSvc) {
		this.customerSvc = customerSvc;
	}
	public List<CustomerDto> getCustomerDtos() {
		return customerDtos;
	}
	public void setCustomerDtos(List<CustomerDto> customerDtos) {
		this.customerDtos = customerDtos;
	}
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	
	@Init
	public void load()
	{
		customerDtos=customerSvc.findAll();
		
	}
}