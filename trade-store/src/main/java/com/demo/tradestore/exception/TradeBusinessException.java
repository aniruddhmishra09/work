/**
 * 
 */
package com.demo.tradestore.exception;

import com.demo.tradestore.exception.errorcode.ErrorCode;

/**
 * @author Aniruddh Mishra
 *
 */
public class TradeBusinessException extends TradeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeBusinessException() {
		super();
		
	}

	public TradeBusinessException(ErrorCode errorCode, Exception ex) {
		super(errorCode, ex);
		
	}

	
	
	

}
