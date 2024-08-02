package com.example.soap_crud_app.service.util;

import com.example.soap_crud_app.entity.Customer;
import io.spring.guides.gs_producing_web_service.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {
    private final ModelMapper modelMapper;

    public Transformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
    public CustomerDTO toCustomerDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }

}
