/**
 * 
 */
package com.demo.tradestore.response;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.demo.tradestore.exception.errorcode.ErrorCode;

/**
 * @author Aniruddh Mishra
 *
 */
public class ErrorResponse {
	
	private String errorId;
	private HttpStatus httpStatus;
	private String errorMessage;
	private Map<ErrorCode, String> errorList;
	
	public ErrorResponse(String errorId, HttpStatus httpStatus, String errorMessage) {
		super();
		this.errorId = errorId;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}
	public ErrorResponse(String errorId, HttpStatus httpStatus, String errorMessage, Map<ErrorCode, String> errorList) {
		super();
		this.errorId = errorId;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.errorList = errorList;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Map<ErrorCode, String> getErrorList() {
		return errorList;
	}
	public void setErrorList(Map<ErrorCode, String> errorList) {
		this.errorList = errorList;
	}
}