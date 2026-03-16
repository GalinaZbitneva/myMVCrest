package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.CustomerMapper;
import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.bootstrap.Bootstrap;
import me.mvcRest.domain.Customer;
import me.mvcRest.repositories.CategoryRepository;
import me.mvcRest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;




@DataJpaTest
class CustomerServiceImplIntegrationTest {
    //здесь захватывается тестирование базы данных

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        //проверяем метод patch
        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertEquals(updatedName,updatedCustomer.getLastName());
        assertNotNull(updatedCustomer);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        //проверяем метод patch
        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertEquals(updatedName,updatedCustomer.getFirstName());
        assertNotNull(updatedCustomer);
        assertEquals(originalLastName,updatedCustomer.getLastName());
    }


        private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found " + customers.size() );

        return customers.get(0).getId();

    }

}