package org.codejudge.product.mapper;

import org.codejudge.product.entity.Product;
import org.codejudge.product.entity.User;
import org.codejudge.product.entity.UserProduct;
import org.codejudge.product.model.product.PurchasedProductResponse;
import org.codejudge.product.model.product.PurchasedProductsResponse;

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
