package com.wholesalestore.service.customer;


import com.wholesalestore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arvindkumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    /*@Query("select o from Order o where o.customer.email=?1")
    List<Order> getCustomerOrders(String email);*/

}
