package dto;

import java.util.Date;

public class ProductDto implements Comparable<ProductDto>{
	private String prodId;
	private String prodName;
	private int prodPrice;
	private Date expDate;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	@Override
	public int compareTo(ProductDto arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
