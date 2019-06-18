package com.ecommerce.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateCartResponse {

    private UUID cartId;
    private String status;

    public CreateCartResponse(UUID cartId, String status) {
        this.cartId = cartId;
        this.status = status;
    }

    @JsonProperty("cartId")
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
