package com.zazbug.blog.exception;

import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public Result apiErrorHandler(){
		return new Result(false, StatusCode.ERROR, apiErrorHandler().getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result defaultErrorHandler(Exception e){
		return new Result(false,StatusCode.ERROR,e.getMessage());
	}

}
