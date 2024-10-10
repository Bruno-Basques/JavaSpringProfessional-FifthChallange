package com.brunobasques_jsp.fifth_challange.dtos;

public class OrderItemDTO {

	private Long productId;
	private Double price;
	private Integer quantity;
	
	public OrderItemDTO() {	
	}
	
	public OrderItemDTO(Long productId, Double price, Integer quantity) {
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getSubTotal() {
		return price * quantity;
	}
}
