package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto updateCategory(CategoryDto category, int categoryId);
	
	void deleteCategory(int categoryId);
	
	CategoryDto getCategoryById(int categoryId);
	
	List<CategoryDto> getAllCategory();
}
