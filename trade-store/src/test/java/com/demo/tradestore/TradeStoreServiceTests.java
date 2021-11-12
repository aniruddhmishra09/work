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

import com.demo.tradestore.dao.TradeDetailsRepository;
import com.demo.tradestore.exception.TradeBusinessException;
import com.demo.tradestore.exception.errorcode.ErrorCode;
import com.demo.tradestore.model.TradeDetails;
import com.demo.tradestore.request.TradeRequest;
import com.demo.tradestore.response.TradeReport;
import com.demo.tradestore.response.TradeResponse;
import com.demo.tradestore.service.TradeStoreServiceImpl;
import com.demo.tradestore.validators.TradeValidator;

/**
 * @author Aniruddh Mishra
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeStoreServiceTests extends TradeStoreApplicationTests {

	@InjectMocks
	private TradeStoreServiceImpl tradeStoreServiceImpl;
	
	@Mock
	private TradeDetailsRepository tradeDetailsRepository;

	@Mock
	private TradeValidator tradeValidator;
	
	
	@BeforeAll
	public  void setup() {
		MockitoAnnotations.initMocks(tradeStoreServiceImpl); 
	}
	
	@Test
	public void testGetTradeReportByIdSucc() {
		String tradeId = "T1";
		TradeDetails tradeDetails = new TradeDetails("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N");
		Mockito.when(tradeDetailsRepository.findByTradeId(tradeId)).thenReturn(tradeDetails);
		
		TradeReport tradeReport = tradeStoreServiceImpl.getTradeReportById(tradeId);
		
		Assert.assertNotNull(tradeReport);
		Assert.assertEquals("T1", tradeReport.getTradeId());
		
	}
	
	@Test
	public void testGetTradeReportByIdEntityNotFound() {
		String tradeId = "T1";
		Mockito.when(tradeDetailsRepository.findByTradeId(tradeId)).thenReturn(null);
		
		try {
			tradeStoreServiceImpl.getTradeReportById(tradeId);
		}catch(TradeBusinessException tbe) {
			Assert.assertNotNull(tbe);
			Assert.assertEquals(ErrorCode.ERROR_1002, tbe.getErrorCode());
		}
	}
	
	@Test
	public void testGetAllTradeReport() {
		
		List<TradeDetails> tradeDetails = new ArrayList<>();
		tradeDetails.add(new TradeDetails("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N"));
		tradeDetails.add(new TradeDetails("T2", 1, "CP-2", "B2", LocalDate.now(), LocalDate.now(), "N"));
		
		Mockito.when(tradeDetailsRepository.findAll()).thenReturn(tradeDetails);
		List<TradeReport> tradeReport = tradeStoreServiceImpl.getTradeReport();
		
		Assert.assertNotNull(tradeReport);
		Assert.assertEquals("T1", tradeReport.get(0).getTradeId());
	}
	
	
	@Test
	public void testGetAllTradeReportForEmptyList() {
		Mockito.when(tradeDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		List<TradeReport> tradeReport = tradeStoreServiceImpl.getTradeReport();
		
		Assert.assertEquals(0, tradeReport.size());
	}
	
	@Test
	public void testDoTrade() {
		TradeDetails  tradeDetails = null;
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now());
		
		Mockito.doNothing().when(tradeValidator).doTradePreValidation(tradeRequest);
		
		Mockito.when(tradeDetailsRepository.save(tradeDetails)).thenReturn(tradeDetails);
		List<TradeReport> tradeReport = tradeStoreServiceImpl.getTradeReport();
		
		Assert.assertEquals(0, tradeReport.size());
	}
	
	@Test
	public void testUpdateTradeWithWrongTradeId() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now());
		
		Mockito.when(tradeDetailsRepository
				.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(new ArrayList<>());
		
		try {
			tradeStoreServiceImpl.updateTrade(tradeRequest);
		}catch(TradeBusinessException tbe) {
			Assert.assertNotNull(tbe);
			Assert.assertEquals(ErrorCode.ERROR_1002, tbe.getErrorCode());
		}
	}
	
	@Test
	public void testUpdateTradeWithEqualVersionId() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-2", "B2", LocalDate.now());
		TradeDetails existingtradeDetails = new TradeDetails("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N");
		List<TradeDetails> tradeDetails = new ArrayList<>();
		tradeDetails.add(existingtradeDetails);
		
		
		Mockito.when(tradeDetailsRepository
				.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(tradeDetails);
		
		Mockito.when(tradeDetailsRepository.save(existingtradeDetails)).thenReturn(existingtradeDetails);
		
		Mockito.doNothing().when(tradeValidator).updateTradeValidation(tradeRequest, existingtradeDetails.getVersion());
		
		
		TradeResponse trade = tradeStoreServiceImpl.updateTrade(tradeRequest);
		
		Assert.assertNotNull(trade);
		Assert.assertEquals("T1", trade.getTradeId());
		
		
	}
	
	
	@Test
	public void testUpdateTradeWithGreaterVersionId() {
		TradeRequest tradeRequest = new TradeRequest("T1", 2, "CP-2", "B2", LocalDate.now());
		TradeDetails existingtradeDetails = new TradeDetails("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N");
		List<TradeDetails> tradeDetails = new ArrayList<>();
		tradeDetails.add(existingtradeDetails);
		
		
		Mockito.when(tradeDetailsRepository
				.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(tradeDetails);
		
		Mockito.when(tradeDetailsRepository.save(existingtradeDetails)).thenReturn(existingtradeDetails);
		
		Mockito.doNothing().when(tradeValidator).updateTradeValidation(tradeRequest, existingtradeDetails.getVersion());
		
		
		TradeResponse trade = tradeStoreServiceImpl.updateTrade(tradeRequest);
		
		Assert.assertNotNull(trade);
		Assert.assertEquals("T1", trade.getTradeId());
		
		
	}
}
