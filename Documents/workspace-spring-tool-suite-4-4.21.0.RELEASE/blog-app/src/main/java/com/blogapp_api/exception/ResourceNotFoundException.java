package com.blogapp_api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String filedName;
	long fieldValue;

	public ResourceNotFoundException(String resourceName, String filedName, long fieldValue) {
		super(String.format("%s not found with %s :%s", resourceName, filedName, fieldValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}

}
