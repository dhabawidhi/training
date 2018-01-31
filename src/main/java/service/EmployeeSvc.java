package service;

import java.util.List;

import dto.EmployeeDto;

public interface EmployeeSvc {
	public List<EmployeeDto> findAll();
	public void save(EmployeeDto employeeDto);
	public void delete(EmployeeDto employeeDto);
	public EmployeeDto findOne(String empId);
	
}
