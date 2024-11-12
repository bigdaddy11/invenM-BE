package com.main.invenmbe.service;

import com.main.invenmbe.entity.Customer;
import com.main.invenmbe.repository.CustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        //return customerRepository.findAll();
        return customerRepository.findAllByIsActiveTrueOrderByCustomerCodeAsc();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // 단일 Customer 저장
    @Transactional
    public Customer saveCustomer(Customer customer) {
        if (customer.getCustomerCode() == null) {
            Long nextVal = (Long) entityManager.createNativeQuery("SELECT nextval('customer_code_seq')").getSingleResult();
            customer.setCustomerCode(String.format("C%05d", nextVal));
        }

        // if (customerRepository.existsByCustomerName(customer.getCustomerName())) {
        //     throw new IllegalArgumentException("이미 존재하는 거래처명입니다.");
        // }
        entityManager.clear();  // 캐시 초기화
        return customerRepository.save(customer);
    }

    // 다중 Customer 저장
    @Transactional
    public List<Customer> saveAll(List<Customer> customers) {
        List<Customer> nonDuplicateCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            // 중복된 거래처명인 경우 저장하지 않음
            if (customer.getCustomerCode() == null) {
                Long nextVal = (Long) entityManager.createNativeQuery("SELECT nextval('customer_code_seq')").getSingleResult();
                customer.setCustomerCode(String.format("C%05d", nextVal));
            }

            nonDuplicateCustomers.add(customer);
        }
        entityManager.clear();  // 캐시 초기화
        return customerRepository.saveAll(customers);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional // 트랜잭션을 추가하여 오류 방지
    public void deactivateCustomersByIds(List<Long> ids) {
        customerRepository.updateIsActiveByIds(ids, false);
    }
}