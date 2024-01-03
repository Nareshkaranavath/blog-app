package com.blogapp_api.payloads;

import java.sql.Date;

import com.blogapp_api.entities.Category;
import com.blogapp_api.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
   private Integer postId;
   
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;

}
