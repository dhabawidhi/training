package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import entity.Employee;
import entity.EmployeePK;

public interface EmployeeDao extends JpaRepository<Employee, EmployeePK>{
	@Query("select e.empName from Employee e")
	public List<Object[]> getName();
	
	@Query(value="select emp_name from m_employee", nativeQuery=true)
	public List<Object[]> getNameFromNative();
}
