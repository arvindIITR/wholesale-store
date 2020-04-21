package com.wholesalestore.wholesalestore.service.customer;



import com.wholesalestore.wholesalestore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author arvindkumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    /*@Query("select o from Order o where o.customer.email=?1")
    List<Order> getCustomerOrders(String email);*/

}
