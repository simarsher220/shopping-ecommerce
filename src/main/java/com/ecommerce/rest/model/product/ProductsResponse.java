package com.ecommerce.rest.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecommerce.rest.entity.Product;

import java.util.List;

public class ProductsResponse {

    private List<Product> products;

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
