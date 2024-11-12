package com.main.invenmbe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.invenmbe.entity.Customer;
import com.main.invenmbe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Validated @RequestBody JsonNode requestBody) {
        try {
            List<Customer> customers = new ArrayList<>();
            
            // 요청이 배열인 경우
            if (requestBody.isArray()) {
                for (JsonNode node : requestBody) {
                    Customer customer = objectMapper.treeToValue(node, Customer.class);
                    customers.add(customer);
                }
            }
            // 요청이 단일 객체인 경우
            else {
                Customer customer = objectMapper.treeToValue(requestBody, Customer.class);
                customers.add(customer);
            }

            // 모든 Customer 객체를 저장
            List<Customer> savedCustomers = customerService.saveAll(customers);
            return ResponseEntity.ok(savedCustomers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("잘못된 요청 형식입니다: " + e.getMessage());
        }
    }

    @PutMapping("/batch")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCustomers(@Validated @RequestBody List<Customer> customerDetailsList) {
        for (Customer customerDetails : customerDetailsList) {
            
            Optional<Customer> customerOptional = customerService.getCustomerById(customerDetails.getId());
            if (customerOptional.isPresent()) {
                
                Customer existingCustomer = customerOptional.get();
                existingCustomer.setCustomerName(customerDetails.getCustomerName());
                existingCustomer.setContact(customerDetails.getContact());
                existingCustomer.setAddress(customerDetails.getAddress());
                existingCustomer.setIsActive(customerDetails.getIsActive());
                customerService.saveCustomer(existingCustomer); // 업데이트 후 저장
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Customer with ID " + customerDetails.getId() + " not found.");
            }
        }
        return ResponseEntity.ok("모든 고객 정보가 성공적으로 업데이트되었습니다.");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateCustomers(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        customerService.deactivateCustomersByIds(ids);
        return ResponseEntity.ok("Selected customers have been deactivated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
