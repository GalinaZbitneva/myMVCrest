package me.mvcRest.controllers.v1;

import me.mvcRest.api.v1.model.CategoryListDTO;
import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.api.v1.model.CustomerListDTO;
import me.mvcRest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping
    public ResponseEntity <CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO (customerService.getAllCustomers()), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        Long longId = Long.valueOf(id);
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(longId),HttpStatus.OK);
    }

    @PostMapping ()
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id,customerDTO),HttpStatus.OK);
    }
//Метод HTTP PATCH используется для частичного обновления существующего ресурса на сервере.
// В отличие от PUT, который заменяет ресурс целиком,







}
