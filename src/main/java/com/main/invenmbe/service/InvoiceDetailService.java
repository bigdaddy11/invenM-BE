package com.main.invenmbe.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.invenmbe.entity.InvoiceDetail;
import com.main.invenmbe.repository.InvoiceDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class InvoiceDetailService {
    @PersistenceContext
    private EntityManager entityManager;

    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailService(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @Transactional
    public List<InvoiceDetail> saveNewDetails(List<InvoiceDetail> invoiceDetails) {
        List<InvoiceDetail> newDetails = invoiceDetails.stream()
                .filter(detail -> detail.getIsNew() != null && detail.getIsNew()) // isNew가 true인 경우만 필터링
                .collect(Collectors.toList());

        return invoiceDetailRepository.saveAll(newDetails);
    }

    // 송장 ID로 상세 조회
    public List<InvoiceDetail> getDetailsByInvoiceId(Long invoiceId) {
        Query query = entityManager.createNativeQuery(
        "SELECT i.*, c.customer_name AS customer_name, p.invoice_name AS invoice_name " +
        "FROM invoice_detail i " +
        "LEFT JOIN customer c ON i.customer_id = c.id " +
        "LEFT JOIN product p ON i.product_code = p.product_code " +
        "WHERE i.invoice_id = :invoiceId",
        "InvoiceDetailWithExtraFields"
        );
        query.setParameter("invoiceId", invoiceId);
        
        List<Object[]> results = query.getResultList(); // Object 배열 반환
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();

    for (Object[] row : results) {
        InvoiceDetail detail = (InvoiceDetail) row[0]; // i.*로 반환된 InvoiceDetail 엔티티

        // 추가 필드 매핑
        if (row[1] != null) {
            detail.setCustomerName((String) row[1]); // customer_name
        }
        if (row[2] != null) {
            detail.setInvoiceName((String) row[2]); // invoice_name
        }

        invoiceDetails.add(detail);
    }

    return invoiceDetails;
    }

    public void updateInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        for (InvoiceDetail detail : invoiceDetails) {
            Optional<InvoiceDetail> existingDetail = invoiceDetailRepository.findById(detail.getId());
            if (existingDetail.isPresent()) {
                InvoiceDetail updatedDetail = existingDetail.get();
                // 수정할 필드들 업데이트
                updatedDetail.setProductCode(detail.getProductCode()); // 송장제품코드
                updatedDetail.setOrderDate(detail.getOrderDate()); // 주문일자
                updatedDetail.setOrderNumber(detail.getOrderNumber()); // 주문번호
                updatedDetail.setProductName(detail.getProductName()); // 상품명
                updatedDetail.setQuantity(detail.getQuantity()); // 수량
                updatedDetail.setSenderName(detail.getSenderName()); // 보내는 사람
                updatedDetail.setSenderPhone1(detail.getSenderPhone1());
                updatedDetail.setSenderPhone2(detail.getSenderPhone2());
                updatedDetail.setRecipientName(detail.getRecipientName()); // 수취인
                updatedDetail.setRecipientPhone1(detail.getRecipientPhone1());
                updatedDetail.setRecipientPhone2(detail.getRecipientPhone2());
                updatedDetail.setRecipientZipcode(detail.getRecipientZipcode());
                updatedDetail.setRecipientAddress(detail.getRecipientAddress());
                updatedDetail.setCustomerId(detail.getCustomerId());
                updatedDetail.setDeliveryMessage(detail.getDeliveryMessage());
                updatedDetail.setDeliveryCompany(detail.getDeliveryCompany()); // 택배사
                updatedDetail.setTrackingNumber(detail.getTrackingNumber()); // 운송장번호
                updatedDetail.setUpdatedDate(LocalDateTime.now()); // 수정일자
                // 기타 필드 업데이트
                invoiceDetailRepository.save(updatedDetail);
            }
        }
    }

    @Transactional
    public void deleteInvoiceDetails(List<Long> ids) {
        invoiceDetailRepository.deleteAllById(ids); // ID 리스트 기반으로 삭제
    }
}

