package com.ecommerce.rest.mapper;

import com.ecommerce.rest.entity.Category;
import com.ecommerce.rest.model.category.CategoriesResponse;
import com.ecommerce.rest.model.category.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static CategoriesResponse getCategoryResponse(List<Category> categories) {
        CategoriesResponse categoriesResponse = new CategoriesResponse();
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setCategoryName(category.getCategoryName());
            responseList.add(categoryResponse);
        }
        categoriesResponse.setCategories(responseList);
        return categoriesResponse;
    }

}
