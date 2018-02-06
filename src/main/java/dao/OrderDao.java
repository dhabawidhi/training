package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Order;
import entity.OrderPK;

public interface OrderDao extends JpaRepository<Order, OrderPK> {
	@Query(value="select t.order_id,t.cust_id,t.total,mc.cust_name from t_order t inner join m_customer mc on t.cust_id=mc.cust_id",nativeQuery=true)
	public List<Object[]> findAllOrder();
}
