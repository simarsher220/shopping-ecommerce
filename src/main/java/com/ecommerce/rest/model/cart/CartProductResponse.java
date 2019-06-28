package com.ecommerce.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartProductResponse {

    private Integer productId;
    private Double price;
    private String name;
    private Double rating;
    private String imageUrl;
    private Integer ratedBy;
    private Integer quantity;

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("ratedBy")
    public Integer getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(Integer ratedBy) {
        this.ratedBy = ratedBy;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
