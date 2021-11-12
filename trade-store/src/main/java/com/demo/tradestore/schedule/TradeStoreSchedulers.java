/**
 * 
 */
package com.demo.tradestore.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.tradestore.constants.CommonConstants;
import com.demo.tradestore.dao.TradeDetailsRepository;
import com.demo.tradestore.model.TradeDetails;

/**
 * @author Aniruddh Mishra
 *
 */

@Component
public class TradeStoreSchedulers {

	@Autowired
	private TradeDetailsRepository tradeDetailsRepository;

	private static final Logger logger = LoggerFactory.getLogger(TradeStoreSchedulers.class);

	//@Scheduled(cron = "0 0 0 * * *")
	@Scheduled(cron = "0 * * * * *")
	public void expireMaturedTradesSchedules() {
		logger.info("Expire Matured Trades Scheduler Started");
		List<TradeDetails> tradeDetails = tradeDetailsRepository
				.findByExpiredAndMaturityDateLessThan(CommonConstants.TRADE_DETAILS_EXPIRED_N, LocalDate.now());

		if (null != tradeDetails && tradeDetails.size() != 0) {
			tradeDetails = tradeDetails.stream().map(s -> new TradeDetails(s, CommonConstants.TRADE_DETAILS_EXPIRED_Y))
					.collect(Collectors.toList());

			tradeDetailsRepository.saveAll(tradeDetails);
			logger.info("Total Trades Expired - {}", tradeDetails.size());
		}
		logger.info("Expire Matured Trades Scheduler Completed");
	}
}
