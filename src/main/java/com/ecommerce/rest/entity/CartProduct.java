package com.ecommerce.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cart_products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartProduct {

    private CartProductKey id;
    private Cart cart;
    private Product product;
    private Integer quantity;

    public CartProduct() {
    }

    public CartProduct(Cart b, Product p) {
        // create primary key
        this.id = new CartProductKey(b.getCartId(), p.getProductId());

        // initialize attributes
        this.cart = b;
        this.product = p;

        // update relationships to assure referential integrity
        p.getQuantities().add(this);
        b.getQuantities().add(this);
    }

    @EmbeddedId
    public CartProductKey getId() {
        return id;
    }

    public void setId(CartProductKey id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @NotNull
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
