package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.EmployeeDao;
import dto.EmployeeDto;
import entity.Employee;
import entity.EmployeePK;
import service.EmployeeSvc;

@Service("employeeSvc") // copy nama interface dan buat jadi huruf kecil nama servicenya
@Transactional
public class EmployeeSvcImpl implements EmployeeSvc {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<EmployeeDto> findAll() {
		// TODO Auto-generated method stub
		List<EmployeeDto> employeeDtos= new ArrayList<EmployeeDto>();
		List<Employee> employees=employeeDao.findAll();
		for(Employee employee:employees)
		{
			EmployeeDto employeeDto=new EmployeeDto();
			employeeDto.setEmpId(employee.getEmpId());
			employeeDto.setEmpName(employee.getEmpName());
			employeeDto.setEmpDOB(employee.getEmpDOB());
			employeeDto.setEmpHeight(employee.getEmpHeight());
			employeeDtos.add(employeeDto);
		}
		
		return employeeDtos;
	}

	@Override
	public void save(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee=new Employee();
		employee.setEmpId(employeeDto.getEmpId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setEmpDOB(employeeDto.getEmpDOB());
		employee.setEmpHeight(employeeDto.getEmpHeight());
		employeeDao.save(employee);
		
	}

	@Override
	public void delete(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		EmployeePK employeePK=new EmployeePK();
		employeePK.setEmpId(employeeDto.getEmpId());
		employeeDao.delete(employeePK);
	}

	@Override
	public EmployeeDto findOne(String empId) {
		// TODO Auto-generated method stub
		EmployeeDto employeeDto= new EmployeeDto();
		EmployeePK employeePK=new EmployeePK();
		employeePK.setEmpId(empId);
		Employee employee=employeeDao.findOne(employeePK);
		
		if(employee!=null)
		{
			employeeDto.setEmpId(employee.getEmpId());
			employeeDto.setEmpName(employee.getEmpName());
			employeeDto.setEmpDOB(employee.getEmpDOB());
			employeeDto.setEmpHeight(employee.getEmpHeight());
			
		}
		return employeeDto;
	}

}
