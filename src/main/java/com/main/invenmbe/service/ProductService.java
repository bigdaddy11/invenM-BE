package com.main.invenmbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.main.invenmbe.entity.Product;
import com.main.invenmbe.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // 모든 상품 일괄 저장
    @Transactional
    public List<Product> saveAll(List<Product> products) {
        List<Product> nonDuplicateProducts = new ArrayList<>();

        for (Product product : products) {
            // productCode가 없으면 시퀀스로 생성
            if (product.getProductCode() == null) {
                Long nextVal = (Long) entityManager.createNativeQuery("SELECT nextval('product_code_seq')").getSingleResult();
                product.setProductCode(String.format("P%05d", nextVal));
            }
            nonDuplicateProducts.add(product);
        }
        entityManager.clear();  // 캐시 초기화
        return productRepository.saveAll(nonDuplicateProducts);
    }

    // 특정 거래처의 활성화된 상품 목록 조회
    public List<Product> getActiveProductsByCustomerId(Long customerId) {
        return productRepository.findAllByCustomerIdOrderByProductCodeAsc(customerId);
    }

    // 모든 거래처의 상품 목록 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 단일 상품 저장
    public Product save(Product product) {
        if (product.getProductCode() == null) {
            Long nextVal = (Long) entityManager.createNativeQuery("SELECT nextval('product_code_seq')").getSingleResult();
            product.setProductCode(String.format("P%05d", nextVal));
        }
        return productRepository.save(product);
    }

    // 상품 삭제 
    @Transactional
    public void deleteProducts(List<Long> ids) {
        productRepository.deleteAllByIdIn(ids);
    }
}
