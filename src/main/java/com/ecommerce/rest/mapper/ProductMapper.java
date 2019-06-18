package com.ecommerce.rest.mapper;

import com.ecommerce.rest.model.product.ProductsResponse;
import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.model.product.ProductResponse;

import java.util.List;

public class ProductMapper {

    public static ProductsResponse getProductsByCategory(List<Product> products) {
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setProducts(products);
        return productsResponse;
    }

    public static Product getProductForCart(Product product) {
        Product cartProduct = product;
        cartProduct.setQuantity(1);
        return cartProduct;
    }

    public static ProductResponse getProductResponseForProduct(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setRatedBy(product.getRatedBy());
        productResponse.setProductId(product.getProductId());
        return productResponse;
    }
}
