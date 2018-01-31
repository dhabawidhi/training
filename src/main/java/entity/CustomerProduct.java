package entity;

import java.util.List;


public class CustomerProduct {
	private int idCust;
	private List<String> daftarProduk;
	
	public int getIdCust() {
		return idCust;
	}
	public void setIdCust(int idCust) {
		this.idCust = idCust;
	}
	public List<String> getDaftarProduk() {
		return daftarProduk;
	}
	public void setDaftarProduk(List<String> daftarProduk) {
		this.daftarProduk = daftarProduk;
	}
	
	
}
