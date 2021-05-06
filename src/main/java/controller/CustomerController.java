package controller;

import dto.ResponseMessenger;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.impl.CustomerServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;
    @GetMapping()
    public ResponseEntity<?> listCustomer(){
        List<Customer> customerList = customerService.findAll();
        if(customerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
   @GetMapping("/{id}")
    public ResponseEntity<?> detailCustomer(@PathVariable Long id){
       Optional<Customer> customer = customerService.findById(id);
       if(!customer.isPresent()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(customer.get(), HttpStatus.OK);
   }
   @PostMapping()
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer){
        if(customer.getName().equals("")){
            return new ResponseEntity<>(new ResponseMessenger("Name is required!"), HttpStatus.OK);
        }
        if(customer.getAge()<=0){
            return new ResponseEntity<>(new ResponseMessenger("Age not valid"),HttpStatus.OK);
        }
            customerService.save(customer);
       return new ResponseEntity<>(new ResponseMessenger("create success!!!"),HttpStatus.OK);
   }
   @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer){
        Optional<Customer> customer1 = customerService.findById(id);
        if(!customer1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer1.get().setName(customer.getName());
        customer1.get().setAge(customer.getAge());
        customerService.save(customer1.get());
        return new ResponseEntity<>(new ResponseMessenger("up date success!"), HttpStatus.OK);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
       if(!customer.isPresent()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       customerService.deleteById(id);
       return new ResponseEntity<>(new ResponseMessenger("delete Success!"), HttpStatus.OK);
   }

}

