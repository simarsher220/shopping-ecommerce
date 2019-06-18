package com.ecommerce.rest.mapper;

import com.ecommerce.rest.entity.UserProduct;
import com.ecommerce.rest.model.product.PurchasedProductsResponse;
import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.model.product.PurchasedProductResponse;

import java.util.ArrayList;
import java.util.List;

public class UserProductMapper {

    public static UserProduct getInstanceForUserAndProduct(User user, Product product) {
        return new UserProduct(user, product);
    }

    public static PurchasedProductsResponse getPurchasedProducts(List<UserProduct> userProducts) {
        PurchasedProductsResponse productsResponse = new PurchasedProductsResponse();
        List<PurchasedProductResponse> responseList = new ArrayList<>();
        for (UserProduct userProduct : userProducts) {
            PurchasedProductResponse response = new PurchasedProductResponse();
            response.setProductId(userProduct.getProduct().getProductId());
            response.setRating(userProduct.getRating());
            responseList.add(response);
        }
        productsResponse.setPurchasedProductResponse(responseList);
        return productsResponse;
    }
}
