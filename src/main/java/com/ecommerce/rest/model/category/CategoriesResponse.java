package com.ecommerce.rest.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoriesResponse {

    private List<CategoryResponse> categories;

    @JsonProperty("categories")
    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}
