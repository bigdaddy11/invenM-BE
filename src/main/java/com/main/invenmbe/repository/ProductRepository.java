package com.main.invenmbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.invenmbe.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 특정 거래처의 활성화된 상품 목록 조회
    List<Product> findByCustomerId(Long customerId);

    List<Product> findAllByCustomerIdOrderByProductCodeAsc(Long customerId);

    // 다중 삭제 메서드
    void deleteAllByIdIn(List<Long> ids);
}
