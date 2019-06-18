package com.ecommerce.rest.mapper;

import com.ecommerce.rest.entity.CartProduct;
import com.ecommerce.rest.entity.Cart;
import com.ecommerce.rest.entity.Product;

public class CartProductMapper {

    public static CartProduct getForCartAndProduct(Cart cart, Product product) {
        CartProduct cartProduct = new CartProduct(cart, product);
        cartProduct.setQuantity(0);
        return cartProduct;
    }

    public static CartProduct updateForCartAndProduct(CartProduct cartProduct, Cart cart, Product product, Integer quantity) {
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        return cartProduct;
    }
}
