package com.exfantasy.template.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exfantasy.template.constant.ResultCode;
import com.exfantasy.template.exception.OperationException;
import com.exfantasy.template.vo.response.ResponseVo;

@ControllerAdvice
public class GenericExceptionHandler {
	
	@ExceptionHandler(OperationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseVo handleCustomException(OperationException ex) {
		return new ResponseVo(ex);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseVo handleAllException(Exception ex) {
		return new ResponseVo(ResultCode.SYSTEM_EXCEPTION, ex.getMessage());
	}
}
