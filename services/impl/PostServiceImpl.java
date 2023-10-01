package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));
		Post post = mapper.map(postDto, Post.class);
		post.setCreatedDate(new Date());
		post.setLastModifiedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post savedPost = postRepo.save(post);
		PostDto savedPostDto = mapper.map(savedPost, PostDto.class);
		return savedPostDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setLastModifiedDate(new Date());
		Post savedPost = postRepo.save(post);
		PostDto savedPostMap = mapper.map(savedPost, PostDto.class);
		return savedPostMap;
	}

	@Override
	public PostDto getPostByPostId(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		PostDto postDto = mapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = postRepo.findAll();
		List<PostDto> postsDto = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public void deletePostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
	}

}
