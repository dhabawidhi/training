package dto;

import java.util.Date;

public class EmployeeDto {
	private String empId;
	private String empName;
	private Date empDOB;
	private int empHeight;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getEmpDOB() {
		return empDOB;
	}
	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}
	public int getEmpHeight() {
		return empHeight;
	}
	public void setEmpHeight(int empHeight) {
		this.empHeight = empHeight;
	}
	
	
}
