package org.codejudge.product.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProductRequest {

    private Integer quantity;

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
