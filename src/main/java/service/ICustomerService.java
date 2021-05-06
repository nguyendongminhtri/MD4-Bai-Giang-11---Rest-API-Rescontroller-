package service;

import model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    void deleteById(Long id);
    Optional<Customer> findById(Long id);
}
