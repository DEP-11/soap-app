package com.example.soap_crud_app.endpoint;

import com.example.soap_crud_app.entity.Customer;
import com.example.soap_crud_app.service.CustomerService;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CustomerEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CustomerService customerService;

    @Autowired
    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCustomerRequest")
    @ResponsePayload
    public void saveCustomer(@RequestPayload SaveCustomerRequest request) {
        customerService.saveCustomer(request.getCustomer());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerListRequest")
    @ResponsePayload
    public GetCustomerListResponse getAllCustomers(@RequestPayload GetCustomerListRequest request) {
        GetCustomerListResponse getCustomerListResponse = new GetCustomerListResponse();
        List<CustomerDTO> customers = customerService.getCustomers();
        getCustomerListResponse.getCustomers().addAll(customers);
        return getCustomerListResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        CustomerDTO customer = customerService.getCustomer(request.getId());
        getCustomerResponse.setCustomer(customer);
        return getCustomerResponse;
    }
}
