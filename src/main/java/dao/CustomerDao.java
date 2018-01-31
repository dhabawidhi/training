package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Customer;
import entity.CustomerPK;

//public interface CustomerDao extends JpaRepository<Customer, CustomerPK>{ // buat yang bukan autoincrement
public interface CustomerDao extends JpaRepository<Customer, Integer>{ // buat yang autoincrement

}
