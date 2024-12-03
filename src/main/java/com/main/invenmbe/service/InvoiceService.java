package com.main.invenmbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.invenmbe.entity.Invoice;
import com.main.invenmbe.repository.InvoiceRepository;

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
}
