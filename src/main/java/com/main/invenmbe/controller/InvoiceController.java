package com.main.invenmbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.main.invenmbe.entity.Customer;
import com.main.invenmbe.entity.Invoice;
import com.main.invenmbe.service.InvoiceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Invoice>> getInvoicesByCustomerId(@PathVariable Long id) {
        List<Invoice> invoices = invoiceService.getInvoicesByCustomerId(id);
        return ResponseEntity.ok(invoices);
    }
   
    // 다중 송장 등록
    @PostMapping
    public ResponseEntity<List<Invoice>> saveInvoices(@Validated @RequestBody List<Invoice> invoices) {
        List<Invoice> savedInvoices = invoiceService.saveAll(invoices);
        return ResponseEntity.ok(savedInvoices);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteInvoices(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().body("삭제할 송장이 선택되지 않았습니다.");
        }
        invoiceService.deleteInvoices(ids);
        return ResponseEntity.ok("Selected invoices have been deleted");
    }
}
