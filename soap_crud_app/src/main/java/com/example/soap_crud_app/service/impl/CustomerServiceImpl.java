package com.example.soap_crud_app.service.impl;

import com.example.soap_crud_app.entity.Customer;
import com.example.soap_crud_app.exception.AddException;
import com.example.soap_crud_app.repository.CustomerRepository;
import com.example.soap_crud_app.service.CustomerService;
import com.example.soap_crud_app.service.util.Transformer;
import io.spring.guides.gs_producing_web_service.CustomerDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Transformer transformer;

    public CustomerServiceImpl(CustomerRepository customerRepository, Transformer transformer) {
        this.customerRepository = customerRepository;
        this.transformer = transformer;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(transformer::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if(optCustomer.isEmpty()) throw new AddException(404, "No Customer Found");
        return transformer.toCustomerDTO(optCustomer.get());
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer customer = transformer.fromCustomerDTO(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {

    }
}
