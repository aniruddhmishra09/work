/**
 * 
 */
package com.demo.tradestore.exception;

import com.demo.tradestore.exception.errorcode.ErrorCode;

/**
 * @author Aniruddh Mishra
 *
 */
public class TradeSystemException extends TradeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeSystemException() {
		super();
	}

	public TradeSystemException(ErrorCode errorCode, Exception ex) {
		super(errorCode, ex);
	}
	
	

}
