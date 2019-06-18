package com.ecommerce.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {

    private Integer productId;
    private Category category;
    private Double price;
    private String name;
    private Integer quantity;
    private Double rating;
    private String imageUrl;
    private Set<CartProduct> quantities;
    private Set<UserProduct> ratings;
    private Integer ratedBy;

    @Id
    @Column(name = "product_id")
    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @NotNull
    @Column(name = "price")
    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotNull
    @Column(name = "product_name")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "rating")
    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull
    @Column(name = "image_url")
    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    public Set<CartProduct> getQuantities() {
        return quantities;
    }

    public void setQuantities(Set<CartProduct> quantities) {
        this.quantities = quantities;
    }

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    public Set<UserProduct> getRatings() {
        return ratings;
    }

    public void setRatings(Set<UserProduct> ratings) {
        this.ratings = ratings;
    }

    @Column(name = "rated_by")
    public Integer getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(Integer ratedBy) {
        this.ratedBy = ratedBy;
    }
}
