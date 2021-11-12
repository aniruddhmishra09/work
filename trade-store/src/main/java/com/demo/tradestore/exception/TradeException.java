/**
 * 
 */
package com.demo.tradestore.exception;

import org.springframework.http.HttpStatus;

import com.demo.tradestore.exception.errorcode.ErrorCode;

/**
 * @author Aniruddh Mishra
 *
 */
public class TradeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String errorId;
	protected String errorMessage;
	protected HttpStatus httpStatus;
	protected ErrorCode errorCode;
	protected Exception exception;
	
	public TradeException() {
		super();
	}

	public TradeException(ErrorCode errorCode, Exception exception) {
		super();
		this.errorCode = errorCode;
		this.errorId = errorCode.getErrorId();
		this.httpStatus = errorCode.getHttpStatus();
		this.errorMessage = errorCode.getErrorMessage();
		this.exception = exception;
	}
	
	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}