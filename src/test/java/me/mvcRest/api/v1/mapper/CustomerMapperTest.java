package me.mvcRest.api.v1.mapper;

import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }
}