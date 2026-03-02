package me.mvcRest.api.v1.mapper;

import me.mvcRest.api.v1.model.CustomerDTO;
import me.mvcRest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper (componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}

//import org.springframework.context.annotation.Bean;  эта строчка нужна
// чтобы мэппер взял нужные поля для конвертирования
//без этого не работает верно