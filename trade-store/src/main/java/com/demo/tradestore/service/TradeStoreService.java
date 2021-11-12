/**
 * 
 */
package com.demo.tradestore.service;

import java.util.List;

import com.demo.tradestore.request.TradeRequest;
import com.demo.tradestore.response.TradeReport;
import com.demo.tradestore.response.TradeResponse;

/**
 * @author Aniruddh Mishra
 *
 */
public interface TradeStoreService {
	TradeResponse doTrade(TradeRequest tradeRequest);
	
	List<TradeReport> getTradeReport();
	
	TradeReport getTradeReportById(String tradeId);
	
	TradeResponse updateTrade(TradeRequest tradeRequest);
}
