package com.shoppingapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shoppingapp.exception.EntityNotFoundException;
import com.shoppingapp.exception.InvalidUserException;
import com.shoppingapp.exception.ProductNotFoundException;
import com.shoppingapp.model.ErrorOutput;
import com.shoppingapp.exception.EntitiesDoesntMatchException;
import com.shoppingapp.exception.EntityAlreadyExistException;
import com.shoppingapp.exception.EntityCannotBeCreated;

@ControllerAdvice
public class ShoppingAppAdvise {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorOutput exception(Exception exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	@ResponseBody
	public ErrorOutput exception(ProductNotFoundException exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	@ExceptionHandler(value = EntityAlreadyExistException.class)
	@ResponseBody
	public ErrorOutput exception(EntityAlreadyExistException exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	@ExceptionHandler(value = EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.ALREADY_REPORTED)
	public ErrorOutput exception(EntityNotFoundException exception) {
		System.out.println("in entity");
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	@ExceptionHandler(value = EntityCannotBeCreated.class)
	@ResponseBody
	public ErrorOutput exception(EntityCannotBeCreated exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.BAD_REQUEST.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	@ExceptionHandler(value = EntitiesDoesntMatchException.class)
	@ResponseBody
	public ErrorOutput exception(EntitiesDoesntMatchException exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.BAD_REQUEST.value());
		error.setDescription(exception.getMessage());
		return error;
	}
	@ExceptionHandler(value = InvalidUserException.class)
	@ResponseBody
	public ErrorOutput exception(InvalidUserException exception) {
		ErrorOutput error = new ErrorOutput();
		error.setStatuscode(HttpStatus.BAD_REQUEST.value());
		error.setDescription(exception.getMessage());
		return error;
	}
}
