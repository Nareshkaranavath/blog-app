package com.blogapp_api.services;

import java.util.List;

import com.blogapp_api.payloads.CategoryDto;

public interface CategoryService {
    
    // Create a new category
    CategoryDto  createCategory(CategoryDto Categorydto);

    // Update an existing category
    CategoryDto  upadteCategory(CategoryDto Categorydto,Integer categoryId);

    // Delete a category
    void deleteCategory(Integer categoryId);

    // Get a single category by ID
    CategoryDto getCategory(Integer categoryId);

    // Get all categories
    List<CategoryDto> getCategories();

    //CategoryDto getCategory(Integer categoryId);
}
