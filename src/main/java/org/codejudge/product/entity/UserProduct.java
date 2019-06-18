package org.codejudge.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_product")
public class UserProduct {

    private UserProductKey id;
    private User user;
    private Product product;
    private Double rating;

    public UserProduct() {
    }

    public UserProduct(User user, Product product) {
        this.id = new UserProductKey(product.getProductId(), user.getUserId());

        this.user = user;
        this.product = product;

        user.getRatings().add(this);
        product.getRatings().add(this);
    }

    @EmbeddedId
    public UserProductKey getId() {
        return id;
    }

    public void setId(UserProductKey id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
