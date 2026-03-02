package me.mvcRest.api.v1.mapper;

import javax.annotation.processing.Generated;
import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.domain.Customer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-02T18:16:01+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName( customer.getFirstName() );
        customerDTO.setLastName( customer.getLastName() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOtoCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerDTO.getFirstName() );
        customer.setLastName( customerDTO.getLastName() );

        return customer;
    }
}
