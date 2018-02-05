package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.EmployeeDto;
import service.EmployeeSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeDetailVmd {
	@WireVariable
	private EmployeeSvc employeeSvc;
	
	private EmployeeDto employeeDto;

	public EmployeeSvc getEmployeeSvc() {
		return employeeSvc;
	}

	public void setEmployeeSvc(EmployeeSvc employeeSvc) {
		this.employeeSvc = employeeSvc;
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}
	
	@Init
	public void load()
	{
		employeeDto=(EmployeeDto) Sessions.getCurrent().getAttribute("dto");
		
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("employee.zul");
		
	}
	
	@Command()
	public void save()
	{
		employeeSvc.save(employeeDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("employee.zul");
	}
}
