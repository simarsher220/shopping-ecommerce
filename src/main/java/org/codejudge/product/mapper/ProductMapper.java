package org.codejudge.product.mapper;

import org.codejudge.product.entity.Product;
import org.codejudge.product.model.product.ProductResponse;
import org.codejudge.product.model.product.ProductsResponse;

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
