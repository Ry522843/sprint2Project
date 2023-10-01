package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private int categoryId;
	
	@NotEmpty(message = "Category Title can not be empty!")
	private String categoryTitle;
	
	private String categoryDescription;
}
