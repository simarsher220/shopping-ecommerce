package com.ecommerce.rest.controller;

import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.error.exception.GenericException;
import com.ecommerce.rest.model.common.SuccessResponse;
import com.ecommerce.rest.model.product.ProductsResponse;
import com.ecommerce.rest.model.product.PurchasedProductsResponse;
import com.ecommerce.rest.model.product.RateProductRequest;
import com.ecommerce.rest.service.ProductService;
import com.ecommerce.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductsResponse getProductsByCategory(@RequestParam("category_id") Integer categoryId ) {
        return productService.getProductsByCategory(categoryId);
    }

    @RequestMapping(value = "/products/{product_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable("product_id") Integer productId) {
        return productService.getProductById(productId);
    }

    @RequestMapping(value = "/purchased_products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PurchasedProductsResponse getPurchasedProducts(@RequestHeader("Authorization") String authorization) throws GenericException {
        return productService.getPurchasedProducts(authorization);
    }

    @RequestMapping(value = "/rate_product/{product_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse rateProduct(@PathVariable("product_id") Integer productId, @RequestBody RateProductRequest rateProductRequest, @RequestHeader("Authorization") String authorization) throws GenericException {
        productService.rateProduct(authorization, productId, rateProductRequest);
        return new SuccessResponse();
    }

    @RequestMapping(value = "/update_rating/{product_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse updateRating(@PathVariable("product_id") Integer productId, @RequestBody RateProductRequest rateProductRequest, @RequestHeader("Authorization") String authorization) throws GenericException {
        productService.updateRating(authorization, productId, rateProductRequest);
        return new SuccessResponse();
    }

}
