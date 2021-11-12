/**
 * 
 */
package com.demo.tradestore.exception.errorcode;

import org.springframework.http.HttpStatus;

/**
 * @author Aniruddh Mishra
 *
 */
public enum ErrorCode {

	ERROR_1001("ERROR_101", "Un Identified Application Error", HttpStatus.INTERNAL_SERVER_ERROR),
	ERROR_1002("ERROR_102", "Requested Trade not found", HttpStatus.NOT_FOUND),
	ERROR_1003("ERROR_103", "Trade maturity date is less than today", HttpStatus.PRECONDITION_FAILED),
	ERROR_1004("ERROR_104", "Trade Pre Validation Failed", HttpStatus.PRECONDITION_FAILED),
	ERROR_1005("ERROR_105", "Invalid Trade Version", HttpStatus.NOT_ACCEPTABLE);

	private String errorId;
	private String errorMessage;
	private HttpStatus httpStatus;

	private ErrorCode(String errorId, String errorMessage, HttpStatus httpStatus) {
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorId() {
		return errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
