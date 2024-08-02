package com.example.soap_crud_app.service;

import com.example.soap_crud_app.entity.Customer;
import io.spring.guides.gs_producing_web_service.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomer(int id);
    void saveCustomer(CustomerDTO customer);
    void deleteCustomer(int id);
}
