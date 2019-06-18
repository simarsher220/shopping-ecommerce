package org.codejudge.product.mapper;

import org.codejudge.product.entity.Category;
import org.codejudge.product.model.category.CategoriesResponse;
import org.codejudge.product.model.category.CategoryResponse;

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
