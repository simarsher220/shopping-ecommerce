package com.ecommerce.rest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CartProductKey implements Serializable {

    private Integer productId;
    private UUID cartId;

    public CartProductKey() {
    }

    public CartProductKey(UUID cartId, Integer productId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "cart_id")
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartProductKey that = (CartProductKey) o;
        return Objects.equals(cartId, that.cartId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }
}
