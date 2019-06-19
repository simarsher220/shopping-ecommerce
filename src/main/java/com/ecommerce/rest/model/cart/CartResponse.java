package com.ecommerce.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private UUID cartId;
    private UUID userId;
    private Long createdAt;
    private List<CartProductResponse> products;
    private Double totalPrice;

    @JsonProperty("cartId")
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("userId")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @JsonProperty("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("products")
    public List<CartProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductResponse> products) {
        this.products = products;
    }

    @JsonProperty("totalPrice")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
