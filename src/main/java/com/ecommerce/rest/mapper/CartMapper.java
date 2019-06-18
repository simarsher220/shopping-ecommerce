package com.ecommerce.rest.mapper;

import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.entity.Cart;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CartMapper {

    public static Cart createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setCartId(UUID.randomUUID());
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zonedDateTime =ldt.atZone(ZoneId.of("Asia/Kolkata"));
        cart.setCreatedAt(zonedDateTime.toInstant().toEpochMilli());
        cart.setUser(user);
        cart.setTotalPrice((double) 0);
        return cart;
    }

    public static Cart addProductToCart(Cart cart, Product product) {
//        cart.getCartProducts().add(product);
//        Double totalPrice = product.getPrice() + cart.getTotalPrice();
//        cart.setTotalPrice(totalPrice);
//        return cart;
        return null;
    }
}
