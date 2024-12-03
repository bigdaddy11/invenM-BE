package com.main.invenmbe.dto;

public class InvoiceDetailDTO {
    private Long id;
    private String customerName;
    private String productName;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public InvoiceDetailDTO(Long id, String customerName, String productName) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
    }

    // Getters and Setters
}
