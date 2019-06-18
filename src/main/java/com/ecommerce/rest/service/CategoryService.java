package com.ecommerce.rest.service;

import com.ecommerce.rest.dao.CategoryRepository;
import com.ecommerce.rest.mapper.CategoryMapper;
import com.ecommerce.rest.entity.Category;
import com.ecommerce.rest.model.category.CategoriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoriesResponse getCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return CategoryMapper.getCategoryResponse(categories);
    }
}
