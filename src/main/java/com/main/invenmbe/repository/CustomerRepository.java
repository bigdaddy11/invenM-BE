package com.main.invenmbe.repository;

import com.main.invenmbe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerCode(String customerCode);
    Optional<Customer> findByCustomerName(String customerName);
    boolean existsByCustomerName(String customerName);
    List<Customer> findAllByOrderByCustomerCodeAsc();

    // isActive가 true인 고객만 customerCode로 정렬하여 조회
    List<Customer> findAllByIsActiveTrueOrderByCustomerCodeAsc();
    
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = :status WHERE c.id IN :ids")
    void updateIsActiveByIds(@Param("ids") List<Long> ids, @Param("status") boolean status);
}
