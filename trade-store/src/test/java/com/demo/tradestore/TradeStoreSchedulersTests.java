/**
 * 
 */
package com.demo.tradestore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.tradestore.constants.CommonConstants;
import com.demo.tradestore.dao.TradeDetailsRepository;
import com.demo.tradestore.model.TradeDetails;
import com.demo.tradestore.schedule.TradeStoreSchedulers;

/**
 * @author Aniruddh Mishra
 *
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeStoreSchedulersTests  extends TradeStoreApplicationTests{

	@InjectMocks
	private TradeStoreSchedulers tradeStoreSchedulers;
	
	@Mock
	private TradeDetailsRepository tradeDetailsRepository;
	
	@BeforeAll
	public  void setup() {
		MockitoAnnotations.initMocks(tradeStoreSchedulers); 
	}
	
	@Test
	public void testExpireSchedulesWhenAvailable() {
		Exception expected = null;
		List<TradeDetails> tradeDetails = new ArrayList<>();
		tradeDetails.add(new TradeDetails("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N"));
		tradeDetails.add(new TradeDetails("T2", 1, "CP-2", "B2", LocalDate.now(), LocalDate.now(), "N"));
		
		Mockito.when(tradeDetailsRepository
				.findByExpiredAndMaturityDateLessThan(CommonConstants.TRADE_DETAILS_EXPIRED_N, LocalDate.now())).thenReturn(tradeDetails);
		
		Mockito.when(tradeDetailsRepository.saveAll(tradeDetails)).thenReturn(tradeDetails);
		
		try {
			tradeStoreSchedulers.expireMaturedTradesSchedules();
		}catch(Exception exp) {
			expected = exp;
		}
		Assert.assertNull(expected);
	}
	
	@Test
	public void testExpireSchedulesWhenNotAvailable() {
		Exception expected = null;
		List<TradeDetails> tradeDetails = null;
		
		Mockito.when(tradeDetailsRepository
				.findByExpiredAndMaturityDateLessThan(CommonConstants.TRADE_DETAILS_EXPIRED_N, LocalDate.now())).thenReturn(tradeDetails);
		
		
		try {
			tradeStoreSchedulers.expireMaturedTradesSchedules();
		}catch(Exception exp) {
			expected = exp;
		}
		Assert.assertNull(expected);
	}
}
