/**
 * 
 */
package com.demo.tradestore.exception;

import java.util.Map;

import com.demo.tradestore.exception.errorcode.ErrorCode;

/**
 * @author Aniruddh Mishra
 *
 */
public class TradeValidationException extends TradeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<ErrorCode, String> errorList;

	public TradeValidationException() {
		super();
	}

	public TradeValidationException(ErrorCode errorCode, Exception ex) {
		super(errorCode, ex);
	}

	public TradeValidationException(ErrorCode errorCode, Map<ErrorCode, String> errorList, Exception ex) {
		super(errorCode,  ex);
		this.errorList = errorList;
	}

	public Map<ErrorCode, String> getErrorList() {
		return errorList;
	}

	public void setErrorList(Map<ErrorCode, String> errorList) {
		this.errorList = errorList;
	}
}
