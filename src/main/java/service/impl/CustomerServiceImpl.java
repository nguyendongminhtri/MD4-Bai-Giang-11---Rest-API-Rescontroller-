package service.impl;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ICustomerRepository;
import service.ICustomerService;

import java.util.List;
import java.util.Optional;


public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
