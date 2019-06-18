package org.codejudge.product.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codejudge.product.entity.Product;

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
