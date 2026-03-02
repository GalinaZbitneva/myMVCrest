package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.CustomerMapper;
import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.domain.Customer;
import me.mvcRest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerRepository,customerMapper);
    }

    @Test
    void getAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1,customer2));

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        assertEquals(2,customerDTOList.size());

    }

    @Test
    void getCustomerById() {
        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer2));
        CustomerDTO customerDTO = customerService.getCustomerById(2L);


        assertEquals(customer2.getFirstName(),customerDTO.getFirstName());

    }

    @Test
    void createNewCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = customerMapper.customerDTOtoCustomer(customerDTO);
        savedCustomer.setId(1L);

        when(customerRepository.save(any())).thenReturn(savedCustomer);

        CustomerDTO customerSavedDTO = customerService.createNewCustomer(customerDTO);
        assertEquals(customerDTO.getFirstName(),customerSavedDTO.getFirstName());
        assertEquals("/api/v1/customer/1", customerSavedDTO.getCustomerUrl());
    }
}