package com.main.invenmbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.invenmbe.entity.Product;
import com.main.invenmbe.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    // 특정 거래처의 활성화된 상품 목록 조회
    @GetMapping("/{customerId}")
    public List<Product> getProducts(@PathVariable Long customerId) {
        return productService.getActiveProductsByCustomerId(customerId);
    }

    // 여러 상품 정보 저장
    @PostMapping("/batch")
    public ResponseEntity<List<Product>> saveProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveAll(products);
        return ResponseEntity.ok(savedProducts);
    }

    // 신규 상품 등록 (단일 및 일괄)
    @PostMapping
    public ResponseEntity<?> createProduct(@Validated @RequestBody JsonNode requestBody) {
        try {
            List<Product> products = new ArrayList<>();
            
            // 요청이 배열인 경우
            if (requestBody.isArray()) {
                for (JsonNode node : requestBody) {
                    Product product = objectMapper.treeToValue(node, Product.class);
                    products.add(product);
                }
            }
            // 요청이 단일 객체인 경우
            else {
                Product product = objectMapper.treeToValue(requestBody, Product.class);
                products.add(product);
            }

            // 모든 Product 객체를 저장
            List<Product> savedProducts = productService.saveAll(products);
            return ResponseEntity.ok(savedProducts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("잘못된 요청 형식입니다: " + e.getMessage());
        }
    }

    // 다중 상품 삭제
    @PostMapping("/delete")
    public ResponseEntity<String> deleteProducts(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        productService.deleteProducts(ids);
        return ResponseEntity.ok("Selected products have been deleted");
    }
}
