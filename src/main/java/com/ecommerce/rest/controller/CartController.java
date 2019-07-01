package com.ecommerce.rest.controller;

import com.ecommerce.rest.error.exception.GenericException;
import com.ecommerce.rest.model.cart.AddProductRequest;
import com.ecommerce.rest.model.cart.CartResponse;
import com.ecommerce.rest.model.cart.UpdateProductRequest;
import com.ecommerce.rest.model.common.SuccessResponse;
import com.ecommerce.rest.service.CartService;
import com.ecommerce.rest.service.ProductService;
import com.ecommerce.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CartController {

    private CartService cartService;
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(value = "/create_cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse createCart(@RequestHeader("Authorization") String authorization) throws GenericException {
        return cartService.createCart(authorization);
    }

    @RequestMapping(value = "/add_cart/{cart_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse addToCart(@PathVariable("cart_id") UUID cartId, @RequestBody AddProductRequest request, @RequestHeader("Authorization") String authorization) throws GenericException {
        cartService.addToCart(authorization, cartId, request);
        return new SuccessResponse();
    }

    @RequestMapping(value = "/update_cart/{cart_id}/{product_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse updateCart(@PathVariable("cart_id") UUID cartId, @PathVariable("product_id") Integer productId, @RequestBody UpdateProductRequest request, @RequestHeader("Authorization") String authorization) throws GenericException {
        cartService.updateCart(authorization, cartId, productId, request);
        return new SuccessResponse();
    }

    @RequestMapping(value = "/clear_cart/{cart_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse clearCart(@PathVariable("cart_id") UUID cartId, @RequestHeader("Authorization") String authorization) throws GenericException {
        cartService.clearCart(authorization, cartId);
        return new SuccessResponse();
    }

    @RequestMapping(value = "/finalize_cart/{cart_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse finalizeCart(@PathVariable("cart_id") UUID cartId, @RequestHeader("Authorization") String authorization) throws GenericException {
        cartService.finalizeCart(authorization, cartId);
        return new SuccessResponse();
    }

    @RequestMapping(value = "/get_cart/{cart_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("cart_id") UUID cartId, @RequestHeader("Authorization") String authorization) throws GenericException {
        return cartService.getCart(authorization, cartId);
    }
}
