package entity;

import java.io.Serializable;

public class ProductPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String prodId;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
