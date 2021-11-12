/**
 * 
 */
package com.demo.tradestore.response;

/**
 * @author Aniruddh Mishra
 *
 */
public class TradeResponse {
	private String tradeId;

	public TradeResponse() {
		super();
	}

	public TradeResponse(String tradeId) {
		super();
		this.tradeId = tradeId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
}