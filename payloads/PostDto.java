package com.blog.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private int postId;
	
	@NotEmpty(message = "postTitle can not be blank")
	private String postTitle;
	
	private String postContent;
	
	private String postImageUrl = "default.png";
	
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
