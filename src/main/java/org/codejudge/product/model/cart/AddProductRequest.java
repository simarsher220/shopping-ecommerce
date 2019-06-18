package org.codejudge.product.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddProductRequest {

    private Integer productId;

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
