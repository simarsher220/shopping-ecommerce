package org.codejudge.product.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codejudge.product.entity.Category;

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
