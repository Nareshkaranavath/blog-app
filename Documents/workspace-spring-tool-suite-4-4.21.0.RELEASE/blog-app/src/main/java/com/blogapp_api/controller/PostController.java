package com.blogapp_api.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp_api.entities.Post;
import com.blogapp_api.entities.User;
import com.blogapp_api.payloads.ApiResponse;
import com.blogapp_api.payloads.CategoryDto;
import com.blogapp_api.payloads.PostDto;
import com.blogapp_api.payloads.UserDto;
import com.blogapp_api.services.CategoryService;
import com.blogapp_api.services.PostService;
import com.blogapp_api.services.UserService;

@RestController
@RequestMapping("/api/")
public class PostController {
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	// create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")

	public ResponseEntity<PostDto> createPost(@PathVariable Integer userId, @PathVariable Integer categoryId,

			@RequestBody PostDto postDto) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostsByUser(@PathVariable Integer userId) {

		// Retrieve posts by user
		List<PostDto> posts = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostsBycategory(@PathVariable Integer categoryId) {
		// Retrieve posts by user
		List<PostDto> posts = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto postDtos = this.postService.updatePost(postDto, postId);
		return new ResponseEntity(postDtos, HttpStatus.OK);
  }

	// delete post
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is sucessfully deleted !!", true);
	}

	// get All post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue ="1",required=false)Integer pageNumber,
		    @RequestParam(value = "pageSize", defaultValue ="5", required=false)Integer pageSize) {
		List<PostDto> allposts = this.postService.getAllPosts(pageNumber, pageSize);
		return new ResponseEntity<List<PostDto>>(allposts, HttpStatus.OK);
	}
	// get post details by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer PostId) {
		PostDto posts = this.postService.getPostById(PostId);

		return new ResponseEntity<PostDto>(posts, HttpStatus.OK);
	}
}
