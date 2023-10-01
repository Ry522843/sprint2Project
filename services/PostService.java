package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDto;

public interface PostService {

	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	public PostDto updatePost(PostDto postDto, Integer postId);
	
	public PostDto getPostByPostId(Integer postId);
	
	public List<PostDto> getAllPost();
	
	public void deletePostById(Integer postId);
}
