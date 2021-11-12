/**
 * 
 */
package com.demo.tradestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tradestore.request.TradeRequest;
import com.demo.tradestore.response.TradeReport;
import com.demo.tradestore.response.TradeResponse;
import com.demo.tradestore.service.TradeStoreService;

/**
 * @author Aniruddh Mishra
 *
 */

@RestController
public class TradeStoreController {

	@Autowired
	@Qualifier("tradeStoreServiceImpl")
	private TradeStoreService tradeStoreService;

	@PostMapping("/trade")
	public TradeResponse doTrade(@RequestBody TradeRequest tradeRequest) {
		return tradeStoreService.doTrade(tradeRequest);
	}

	@GetMapping("/trade/{tradeId}")
	public TradeReport getTradeReportById(@PathVariable String tradeId) {
		return tradeStoreService.getTradeReportById(tradeId);
	}

	@GetMapping("/trade")
	public List<TradeReport> getTradeReport() {
		return tradeStoreService.getTradeReport();
	}

	@PutMapping("/trade")
	public TradeResponse updateTrade(@RequestBody TradeRequest tradeRequest) {
		return tradeStoreService.updateTrade(tradeRequest);
	}
}
