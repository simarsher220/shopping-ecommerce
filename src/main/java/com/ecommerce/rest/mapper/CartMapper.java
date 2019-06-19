package com.ecommerce.rest.mapper;

import com.ecommerce.rest.entity.CartProduct;
import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.entity.Cart;
import com.ecommerce.rest.model.cart.CartProductResponse;
import com.ecommerce.rest.model.cart.CartResponse;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public static CartResponse getInstanceForCart(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        List<CartProductResponse> responseList = new ArrayList<>();
        cartResponse.setCartId(cart.getCartId());
        cartResponse.setUserId(cart.getUser().getUserId());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        cartResponse.setCreatedAt(cart.getCreatedAt());
        if (CollectionUtils.isEmpty(cart.getQuantities())) {
            cartResponse.setProducts(responseList);
        }
        else {
            for (CartProduct cartProduct : cart.getQuantities()) {
                CartProductResponse cartProductResponse = new CartProductResponse();
                cartProductResponse.setProductId(cartProduct.getId().getProductId());
                cartProductResponse.setQuantity(cartProduct.getQuantity());
                responseList.add(cartProductResponse);
            }
            cartResponse.setProducts(responseList);
        }
        return cartResponse;
    }
}
