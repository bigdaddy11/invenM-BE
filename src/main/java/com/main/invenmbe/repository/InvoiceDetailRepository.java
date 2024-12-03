package com.main.invenmbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.invenmbe.entity.InvoiceDetail;

import jakarta.persistence.SqlResultSetMapping;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    @Query(value = "SELECT i.*, c.customer_name AS customer_name, p.product_name AS product_name " +
                   "FROM invoice_detail i " +
                   "LEFT JOIN customer c ON i.customer_id = c.id " +
                   "LEFT JOIN product p ON i.product_code = p.product_code " +
                   "WHERE i.invoice_id = :invoiceId", nativeQuery = true)
    List<InvoiceDetail> InvoiceDetailWithExtraFields(@Param("invoiceId") Long invoiceId);
}

