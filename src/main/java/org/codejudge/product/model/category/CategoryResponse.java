package org.codejudge.product.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryResponse {

    private Integer categoryId;
    private String categoryName;

    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
