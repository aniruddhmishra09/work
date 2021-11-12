/**
 * 
 */
package com.demo.tradestore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.tradestore.constants.CommonConstants;
import com.demo.tradestore.dao.TradeDetailsRepository;
import com.demo.tradestore.exception.TradeBusinessException;
import com.demo.tradestore.exception.errorcode.ErrorCode;
import com.demo.tradestore.model.TradeDetails;
import com.demo.tradestore.request.TradeRequest;
import com.demo.tradestore.response.TradeReport;
import com.demo.tradestore.response.TradeResponse;
import com.demo.tradestore.validators.TradeValidator;

/**
 * @author Aniruddh Mishra
 *
 */

@Service("tradeStoreServiceImpl")
public class TradeStoreServiceImpl implements TradeStoreService {

	@Autowired
	private TradeDetailsRepository tradeDetailsRepository;

	@Autowired
	private TradeValidator tradeValidator;

	@Override
	public TradeResponse doTrade(TradeRequest tradeRequest) {
		TradeDetails tradeDetails = new TradeDetails();

		tradeValidator.doTradePreValidation(tradeRequest);

		BeanUtils.copyProperties(tradeRequest, tradeDetails);
		tradeDetails.setCreatedDate(LocalDate.now());
		tradeDetails.setExpired(CommonConstants.TRADE_DETAILS_EXPIRED_N);

		tradeDetails = tradeDetailsRepository.save(tradeDetails);

		return new TradeResponse(tradeDetails.getTradeId());
	}

	@Override
	public TradeReport getTradeReportById(String tradeId) {
		TradeReport tradeReport = new TradeReport();
		TradeDetails tradeDetails = tradeDetailsRepository.findByTradeId(tradeId);

		if (null == tradeDetails) {
			throw new TradeBusinessException(ErrorCode.ERROR_1002, new RuntimeException("Entity Not Found"));
		}

		BeanUtils.copyProperties(tradeDetails, tradeReport);
		return tradeReport;
	}

	@Override
	public List<TradeReport> getTradeReport() {
		List<TradeReport> tradeReport = new ArrayList<>();
		List<TradeDetails> tradeDetails = tradeDetailsRepository.findAll();
		tradeDetails.forEach(s -> {
			TradeReport trade = new TradeReport();
			BeanUtils.copyProperties(s, trade);
			tradeReport.add(trade);
		});
		return tradeReport;
	}

	@Override
	public TradeResponse updateTrade(TradeRequest tradeRequest) {
		TradeDetails tradeDetails = null;
		TradeDetails existingtradeDetails = null;

		List<TradeDetails> existingtradeDetailList = tradeDetailsRepository
				.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId());

		
		if (existingtradeDetailList.size() == 0) {
			throw new TradeBusinessException(ErrorCode.ERROR_1002, new RuntimeException("Entity Not Found"));
		}

		existingtradeDetails = existingtradeDetailList.get(0);
		tradeValidator.updateTradeValidation(tradeRequest, existingtradeDetails.getVersion());

		if (tradeRequest.getVersion() == existingtradeDetails.getVersion()) {
			BeanUtils.copyProperties(tradeRequest, existingtradeDetails, "tradeId");
			tradeDetailsRepository.save(existingtradeDetails);
		} else {
			tradeDetails = new TradeDetails();
			BeanUtils.copyProperties(tradeRequest, tradeDetails);
			tradeDetails.setCreatedDate(LocalDate.now());
			tradeDetails.setExpired(CommonConstants.TRADE_DETAILS_EXPIRED_N);
			tradeDetailsRepository.save(tradeDetails);
		}

		return new TradeResponse(tradeRequest.getTradeId());
	}
}
