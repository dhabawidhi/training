package entity;



import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="m_employee")
@IdClass(EmployeePK.class)
public class Employee {
	
	private String empId;
	
	private String empName;
	private Date empDOB;
	private int empHeight;
	
	@Id
	@Column(name="emp_id")
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Column(name="emp_name")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name="emp_dob")
	public Date getEmpDOB() {
		return empDOB;
	}
	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}
	
	@Column(name="emp_height")
	public int getEmpHeight() {
		return empHeight;
	}
	public void setEmpHeight(int empHeight) {
		this.empHeight = empHeight;
	}
	
	
}
