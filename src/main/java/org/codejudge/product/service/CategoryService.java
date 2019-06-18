package org.codejudge.product.service;

import org.codejudge.product.dao.CategoryRepository;
import org.codejudge.product.entity.Category;
import org.codejudge.product.mapper.CategoryMapper;
import org.codejudge.product.model.category.CategoriesResponse;
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
