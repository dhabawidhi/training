package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.CustomerDto;
import service.CustomerSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerDetailVmd {
	@WireVariable
	private CustomerSvc customerSvc;
	
	private CustomerDto customerDto;

	public CustomerSvc getCustomerSvc() {
		return customerSvc;
	}

	public void setCustomerSvc(CustomerSvc customerSvc) {
		this.customerSvc = customerSvc;
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
		customerDto=(CustomerDto) Sessions.getCurrent().getAttribute("dto");
		
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("customer.zul");
		
	}
	
	@Command()
	public void save()
	{
		
		customerSvc.save(customerDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("customer.zul");
	}
}
