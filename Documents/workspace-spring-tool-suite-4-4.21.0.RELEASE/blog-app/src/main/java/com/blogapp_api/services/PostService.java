package com.blogapp_api.services;

import java.util.List;

import com.blogapp_api.entities.Post;
import com.blogapp_api.payloads.PostDto;

public interface PostService {

	// Create a new post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // Update an existing post
    PostDto updatePost(PostDto postDto, Integer postId);

    // Delete a post
    void deletePost(Integer postId);

    // Get all posts
    List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);

    // Get post details  by ID
    PostDto getPostById(Integer postId);
    
    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);
    
    //get all post by user
    List<PostDto> getPostsByUser(Integer userId);
    
    //search posts
    List<PostDto> searchPosts(String keyword);

	

}
