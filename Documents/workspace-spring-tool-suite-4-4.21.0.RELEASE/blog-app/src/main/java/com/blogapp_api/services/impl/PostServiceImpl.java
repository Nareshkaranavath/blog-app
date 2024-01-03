package com.blogapp_api.services.impl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.blogapp_api.entities.Category;
import com.blogapp_api.entities.Post;
import com.blogapp_api.entities.User;
import com.blogapp_api.exception.ResourceNotFoundException;
import com.blogapp_api.payloads.PostDto;
import com.blogapp_api.repositories.CategoryRepo;
import com.blogapp_api.repositories.PostRepo;
import com.blogapp_api.repositories.UserRepo;
import com.blogapp_api.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	// create post implementation
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		// post.setAddedDate( new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepo.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	// update post method implementation
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post updatePost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		// Update the existing post with the data from the incoming PostDto
		updatePost.setTitle(postDto.getTitle());
		updatePost.setContent(postDto.getContent());
		updatePost.setImageName(postDto.getImageName());
		// Update other fields as needed

		// Save the updated post
		Post updatedPost = postRepo.save(updatePost);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	// delete post implementation
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(posts);
	}

	// get all posts
	@Override
	public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize) {

		PageRequest p =  PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		
		// Map Post entities to PostDto using ModelMapper
		List<PostDto> postDtos = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// Retrieve a post by its ID from the repository
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		// Map the Post entity to a PostDto using ModelMapper
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User users = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(users);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public PostDto createPost(PostDto postDto) { // TODO Auto-generated
	 * method stub return null; }
	 */
}
