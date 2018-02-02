package vmd;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import dto.EmployeeDto;
import service.EmployeeSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeVmd {
	@WireVariable
	private EmployeeSvc employeeSvc;
	
	private List<EmployeeDto> employeeDtos;
	private EmployeeDto employeeDto;
	
	
	
	public EmployeeSvc getEmployeeSvc() {
		return employeeSvc;
	}

	public void setEmployeeSvc(EmployeeSvc employeeSvc) {
		this.employeeSvc = employeeSvc;
	}

	public List<EmployeeDto> getEmployeeDtos() {
		return employeeDtos;
	}

	public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
		this.employeeDtos = employeeDtos;
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
		employeeDtos=employeeSvc.findAll();
		
	}
}
