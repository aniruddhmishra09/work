/**
 * 
 */
package com.demo.tradestore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.tradestore.exception.errorcode.ErrorCode;
import com.demo.tradestore.response.ErrorResponse;

/**
 * @author Aniruddh Mishra
 *
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.ERROR_1001.getErrorId(),
				ErrorCode.ERROR_1001.getHttpStatus(), ErrorCode.ERROR_1001.getErrorMessage());
		this.printUnhandledExceptionLogs(ex, ErrorCode.ERROR_1001);
		return new ResponseEntity<>(errorResponse, ErrorCode.ERROR_1001.getHttpStatus());
	}

	@ExceptionHandler(TradeBusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(TradeBusinessException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getHttpStatus(), ex.getErrorMessage());
		this.printCustomExceptionLogs(ex);
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}

	@ExceptionHandler(TradeSystemException.class)
	public ResponseEntity<ErrorResponse> handleSystemException(TradeSystemException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getHttpStatus(), ex.getErrorMessage());
		this.printCustomExceptionLogs(ex);
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}

	@ExceptionHandler(TradeValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(TradeValidationException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getHttpStatus(), ex.getErrorMessage(),
				ex.getErrorList());
		this.printCustomExceptionLogs(ex);
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}

	private void printCustomExceptionLogs(TradeException ex) {
		if (ex instanceof TradeValidationException || ex instanceof TradeBusinessException) {
			logger.info("Error ID - {} , Error Message - {} ", ex.getErrorId(), ex.getErrorMessage());
		} else {
			logger.error("Error ID - {} , Error Message - {} , Exception Trace - ", ex.getErrorId(),
					ex.getErrorMessage(), ex);
		}

	}

	private void printUnhandledExceptionLogs(Exception ex, ErrorCode errorCode) {
		logger.error("Error ID - {} , Error Message - {} , Exception Trace - ", errorCode.getErrorId(),
				errorCode.getErrorMessage(), ex);
	}

}
