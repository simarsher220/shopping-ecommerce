package org.codejudge.product.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PurchasedProductsResponse {

    private List<PurchasedProductResponse> purchasedProductResponse;

    @JsonProperty("products")
    public List<PurchasedProductResponse> getPurchasedProductResponse() {
        return purchasedProductResponse;
    }

    public void setPurchasedProductResponse(List<PurchasedProductResponse> purchasedProductResponse) {
        this.purchasedProductResponse = purchasedProductResponse;
    }
}
