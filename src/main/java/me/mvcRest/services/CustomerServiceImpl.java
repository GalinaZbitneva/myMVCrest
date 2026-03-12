package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.CustomerMapper;
import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.domain.Customer;
import me.mvcRest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElseThrow(RuntimeException::new));
        customerDTO.setCustomerUrl("/api/v1/customer/" + id);
        return customerDTO;
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
        return customerDTO;
    }

        @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        //созданного на странице customerDTO приводим  виду customer
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        //теперь customer сохраняем в репозитории где ему присваивается айди


//            Customer savedCustomer = customerRepository.save(customer);
//        //теперь уже сохраненного в репозитории customer приводим к виду DTO и возвращаем
//        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
//        returnCustomerDTO.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
//
//        return returnCustomerDTO;

           return saveAndReturnDTO(customer);
    }


    //update?
    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }
}
