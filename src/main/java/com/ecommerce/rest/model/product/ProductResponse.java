package com.ecommerce.rest.model.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Integer productId;
    private Double price;
    private String name;
    private Double rating;
    private String imageUrl;
    private Integer ratedBy;

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
}
