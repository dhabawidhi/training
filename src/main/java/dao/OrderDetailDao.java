package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import entity.OrderDetail;
import entity.OrderDetailPK;

public interface OrderDetailDao extends JpaRepository<OrderDetail, OrderDetailPK> {
	
	@Query("select o,p.prodName from OrderDetail o, Product p where o.prodId = p.prodId and o.orderId= ? ")
	public List<Object[]> findAllOrderDetail(String search);
	
//	@Query(value="select tod.order_id from t_order_detail tod inner join m_product mp on tod.prod_id=mp.prod_id", nativeQuery=true)
//	public List<Object[]> findAllOrderDetail();
	
	@Modifying
	@Query(value="delete from OrderDetail o where o.orderId = ? ")
	public void deleteOrder(String orderId);
}
