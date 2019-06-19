package com.ecommerce.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartProductResponse {

    private Integer productId;
    private Integer quantity;

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
