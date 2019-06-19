package com.ecommerce.rest.service;

import com.ecommerce.rest.dao.CartProductRepository;
import com.ecommerce.rest.entity.*;
import com.ecommerce.rest.mapper.CartProductMapper;
import com.ecommerce.rest.dao.CartRepository;
import com.ecommerce.rest.dao.UserProductRepository;
import com.ecommerce.rest.error.exception.GenericException;
import com.ecommerce.rest.mapper.CartMapper;
import com.ecommerce.rest.mapper.UserMapper;
import com.ecommerce.rest.mapper.UserProductMapper;
import com.ecommerce.rest.model.cart.AddProductRequest;
import com.ecommerce.rest.model.cart.CartResponse;
import com.ecommerce.rest.model.cart.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private CartRepository cartRepository;
    private UserService userService;
    private ProductService productService;
    private CartProductRepository cartProductRepository;
    private UserProductRepository userProductRepository;


    @Autowired
    public CartService(CartRepository cartRepository, UserService userService, ProductService productService, CartProductRepository cartProductRepository, UserProductRepository userProductRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartProductRepository = cartProductRepository;
        this.userProductRepository = userProductRepository;
    }

    public CartResponse createCart(String authorization) throws GenericException {
        User user = userService.getValidUser(authorization);
        Cart cart = CartMapper.createCartForUser(user);
        cartRepository.saveAndFlush(cart);
        UserMapper.updateUserWithCart(user, cart);
        userService.updateUser(user);
        return CartMapper.getInstanceForCart(cart);
    }

    public void addToCart(String authorization, UUID cartId, AddProductRequest request) throws GenericException {
        userService.getValidUser(authorization);
        Cart cart = cartRepository.findByCartId(cartId);
        Product product = productService.getProductById(request.getProductId());
        if (product.getQuantity() == 0) {
            // TODO product out of stock
            throw new GenericException("Product out of stock!!", HttpStatus.BAD_REQUEST);
        }
        CartProduct cartProduct = CartProductMapper.getForCartAndProduct(cart, product);
        cartProductRepository.saveAndFlush(cartProduct);
        update(cart, product, cartProduct, 1);
    }

    public void updateCart(String authorization, UUID cartId, Integer productId, UpdateProductRequest request) throws GenericException {
        userService.getValidUser(authorization);
        Cart cart = cartRepository.findByCartId(cartId);
        Product product = productService.getProductById(productId);
        CartProduct cartProduct = cartProductRepository.findByCartAndProduct(cart, product);
        if (cartProduct == null) {
            throw new GenericException("Product not added yet!", HttpStatus.BAD_REQUEST);
        }
        Integer quantity = request.getQuantity();
        if (cartProduct.getQuantity() + quantity < 0) {
            throw new GenericException("Quantity can't be more than in the cart!!", HttpStatus.BAD_REQUEST);
        }
        if (product.getQuantity() - quantity < 0) {
            throw new GenericException("Quantity can't be more than in the inventory!!", HttpStatus.BAD_REQUEST);
        }
        update(cart, product, cartProduct, quantity);
    }

    public void clearCart(String authorization, UUID cartId) throws GenericException {
        userService.getValidUser(authorization);
        Cart cart = cartRepository.findByCartId(cartId);
        cartProductRepository.deleteByCart(cart);
        cart.setTotalPrice((double) 0);
        cartRepository.saveAndFlush(cart);
    }

    public void finalizeCart(String authorization, UUID cartId) throws GenericException {
        User user = userService.getValidUser(authorization);
        Cart cart = cartRepository.findByCartId(cartId);
        List<CartProduct> cartProducts = cartProductRepository.findAllByCart(cart);
        for (CartProduct cartProduct : cartProducts) {
            Product product = cartProduct.getProduct();
            UserProduct userProduct = UserProductMapper.getInstanceForUserAndProduct(user, product);
            userProductRepository.saveAndFlush(userProduct);
        }
        clearCart(authorization, cartId);
    }

    public CartResponse getCart(String authorization, UUID cartId) throws GenericException {
        userService.getValidUser(authorization);
        Cart cart = cartRepository.findByCartId(cartId);
        return CartMapper.getInstanceForCart(cart);
    }

    private void update(Cart cart, Product product, CartProduct cartProduct, Integer quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        Double productPrice = product.getPrice();
        productService.updateProduct(product);
        cart.setTotalPrice(cart.getTotalPrice() + (quantity * productPrice));
        cartRepository.saveAndFlush(cart);
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        if (cartProduct.getQuantity() == 0) {
            cartProductRepository.deleteByCartAndProduct(cart, product);
        }
        else {
            cartProductRepository.saveAndFlush(cartProduct);
        }
    }
}
