package com.blogapp_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.blogapp_api.entities.Category;
import com.blogapp_api.exception.ResourceNotFoundException;
import com.blogapp_api.payloads.CategoryDto;
import com.blogapp_api.repositories.CategoryRepo;
import com.blogapp_api.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedcat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto upadteCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategeoryDescription());
		Category updatedcat = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catory id", categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category>categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream()
		 .map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
         .collect(Collectors.toList());
		 return catDtos;
	    }

}
