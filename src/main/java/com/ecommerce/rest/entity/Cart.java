package com.ecommerce.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {

    private UUID cartId;
    private Long createdAt;
    private User user;
    private Set<CartProduct> quantities;
    private Double totalPrice;

    @Id
    @Column(name = "cart_id")
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    @NotNull
    @Column(name = "created_at")
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonIgnoreProperties("cart")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinTable(name = "cart_products",
//            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false))

    @OneToMany(mappedBy = "cart")
    public Set<CartProduct> getQuantities() {
        return quantities;
    }

    public void setQuantities(Set<CartProduct> quantities) {
        this.quantities = quantities;
    }

    @NotNull
    @Column(name = "total_price")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
