package org.codejudge.product.controller;

import org.codejudge.product.entity.Product;
import org.codejudge.product.error.exception.GenericException;
import org.codejudge.product.model.common.SuccessResponse;
import org.codejudge.product.model.product.ProductsResponse;
import org.codejudge.product.model.product.PurchasedProductsResponse;
import org.codejudge.product.model.product.RateProductRequest;
import org.codejudge.product.service.ProductService;
import org.codejudge.product.service.UserService;
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
