package com.main.invenmbe.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.invenmbe.entity.InvoiceDetail;
import com.main.invenmbe.service.InvoiceDetailService;

@RestController
@RequestMapping("/api/invoice-details")
public class InvoiceDetailController {

    private final InvoiceDetailService invoiceDetailService;

    public InvoiceDetailController(InvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerInvoiceDetails(@RequestBody List<InvoiceDetail> invoiceDetails) {
        try {
            List<InvoiceDetail> savedDetails = invoiceDetailService.saveNewDetails(invoiceDetails);
            return ResponseEntity.ok(savedDetails.size() + "개의 송장 상세가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("송장 상세 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 특정 송장 상세 조회
    @GetMapping("/{invoiceId}")
    public ResponseEntity<List<InvoiceDetail>> getInvoiceDetailsByInvoiceId(@PathVariable("invoiceId") Long invoiceId) {
        try {
            List<InvoiceDetail> details = invoiceDetailService.getDetailsByInvoiceId(invoiceId);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @PutMapping("/batch")
    public ResponseEntity<String> updateInvoiceDetails(@RequestBody List<InvoiceDetail> invoiceDetails) {
        try {
            invoiceDetailService.updateInvoiceDetails(invoiceDetails);
            return ResponseEntity.ok("송장 상세 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("송장 상세 수정에 실패했습니다: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteInvoiceDetails(@RequestBody List<Long> ids) {
        try {
            invoiceDetailService.deleteInvoiceDetails(ids);
            return ResponseEntity.ok("선택된 항목이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 중 오류가 발생했습니다.");
        }
    }
}