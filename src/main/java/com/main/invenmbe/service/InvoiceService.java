package com.main.invenmbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.invenmbe.entity.Invoice;
import com.main.invenmbe.repository.InvoiceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> getInvoicesByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }
 
    @Transactional
    public List<Invoice> saveAll(List<Invoice> invoices) {
        List<Invoice> savedInvoices = new ArrayList<>();

        for (Invoice invoice : invoices) {
            savedInvoices.add(invoice);
        }

        return invoiceRepository.saveAll(savedInvoices);
    }

    @Transactional
    public void deleteInvoices(List<Long> ids) {
        invoiceRepository.deleteAllById(ids);
    }

    @Transactional
    public void updateInvoices(List<Invoice> updates) {
        for (Invoice update : updates) {
            Invoice invoice = invoiceRepository.findById(update.getId())
                    .orElseThrow(() -> new IllegalArgumentException("송장을 찾을 수 없습니다. ID: " + update.getId()));

            invoice.setInvoiceName(update.getInvoiceName());
            invoice.setUpdatedDate(LocalDateTime.now());
            invoiceRepository.save(invoice);
        }
    }

}
