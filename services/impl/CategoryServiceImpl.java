package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepo.save(category);
		CategoryDto savedCategoryDto = modelMapper.map(savedCategory, CategoryDto.class);
		return savedCategoryDto;
	}
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = categoryRepo.save(category);
		CategoryDto updatedCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);
		return updatedCategoryDto;
	}
	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		categoryRepo.delete(category);
	}
	
	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoriesDto;
	}
	
}
