package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name="m_customer")
//@IdClass(CustomerPK.class) // buat autoincrement dikomen
public class Customer {
	private int custId;
	private String custName;
	private String custAddress;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // buat ID yang integer autoincrement
	@Column(name="cust_id",columnDefinition = "serial")
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	@Column(name="cust_name")
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	@Column(name="cust_address")
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
		
}
