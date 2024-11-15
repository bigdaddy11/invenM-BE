package com.main.invenmbe.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId; // 거래처 ID

    @NotNull
    private String productCode; // 송장제품코드

    @NotNull
    private Long invoiceId; // 송장 ID

    private LocalDate orderDate; // 주문일자
    private String orderNumber; // 주문번호
    private String productName; // 상품명
    private Integer quantity; // 수량

    private String senderName; // 보내는 사람
    private String senderPhone1;
    private String senderPhone2;

    private String recipientName; // 수취인
    private String recipientPhone1;
    private String recipientPhone2;

    private String recipientZipcode;
    private String recipientAddress;
    private String deliveryMessage;

    private String deliveryCompany; // 택배사
    private String trackingNumber; // 운송장번호

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Size(max = 50)
    private String createdBy;

    @Transient // DB에 저장되지 않음
    private Boolean isNew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone1() {
        return senderPhone1;
    }

    public void setSenderPhone1(String senderPhone1) {
        this.senderPhone1 = senderPhone1;
    }

    public String getSenderPhone2() {
        return senderPhone2;
    }

    public void setSenderPhone2(String senderPhone2) {
        this.senderPhone2 = senderPhone2;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone1() {
        return recipientPhone1;
    }

    public void setRecipientPhone1(String recipientPhone1) {
        this.recipientPhone1 = recipientPhone1;
    }

    public String getRecipientPhone2() {
        return recipientPhone2;
    }

    public void setRecipientPhone2(String recipientPhone2) {
        this.recipientPhone2 = recipientPhone2;
    }

    public String getRecipientZipcode() {
        return recipientZipcode;
    }

    public void setRecipientZipcode(String recipientZipcode) {
        this.recipientZipcode = recipientZipcode;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }

    public void setDeliveryMessage(String deliveryMessage) {
        this.deliveryMessage = deliveryMessage;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

