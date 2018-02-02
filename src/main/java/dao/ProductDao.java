package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Product;
import entity.ProductPK;

public interface ProductDao extends JpaRepository<Product, ProductPK> {
	@Query(value="select p.prodName from Product p")
	public List<Object[]> getName();
	
	@Query(value="select prod_price from m_product",nativeQuery=true)
	public List<Object[]> getNameNative();
	
	@Query(value="select * from m_product where prod_name like ? ", nativeQuery=true)
	public List<Product> findAll(String search);
}
