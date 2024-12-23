package com.main.invenmbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)  // 엔티티 리스너 추가
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "customerCode"),
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String customerCode;

    @NotNull
    @Size(min = 1, max = 100)
    private String customerName;

    @Size(max = 20)
    private String contact;

    @Size(max = 255)
    private String address;

    @NotNull
    @JsonProperty("isActive")
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Size(max = 50)
    private String createdBy;

    @Size(max = 10)
    private String ceoName; //대표자명

    @Size(max = 100)
    private String repName1; //담당자명1

    @Size(max = 100)
    private String repName2; //담당자명2

    @Size(max = 20)
    private String repNumber1;  //담당자번호1

    @Size(max = 20)
    private String repNumber2;  //담당자번호2

    @Size(max = 100)
    private String bankAcount;  //통장사본

    @Size(max = 100)
    private String license;  //사업자등록증

    public String getRepName1() {
        return repName1;
    }

    public void setRepName1(String repName1) {
        this.repName1 = repName1;
    }

    public String getRepName2() {
        return repName2;
    }

    public void setRepName2(String repName2) {
        this.repName2 = repName2;
    }

    public String getRepNumber1() {
        return repNumber1;
    }

    public void setRepNumber1(String repNumber1) {
        this.repNumber1 = repNumber1;
    }

    public String getRepNumber2() {
        return repNumber2;
    }

    public void setRepNumber2(String repNumber2) {
        this.repNumber2 = repNumber2;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getBankAcount() {
        return bankAcount;
    }

    public void setBankAcount(String bankAcount) {
        this.bankAcount = bankAcount;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
