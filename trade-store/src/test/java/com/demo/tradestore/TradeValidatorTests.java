/**
 * 
 */
package com.demo.tradestore;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.demo.tradestore.exception.TradeValidationException;
import com.demo.tradestore.exception.errorcode.ErrorCode;
import com.demo.tradestore.request.TradeRequest;
import com.demo.tradestore.validators.TradeValidator;

/**
 * @author Aniruddh Mishra
 *
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeValidatorTests extends TradeStoreApplicationTests{

	@InjectMocks
	private TradeValidator tradeValidator;
	
	@BeforeAll
	public  void setup() {
		MockitoAnnotations.initMocks(tradeValidator); 
	}
	
	@Test
	public void testDoTradePreValidationInvalidMaturityDate() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now().minusDays(2L));
		try {
			tradeValidator.doTradePreValidation(tradeRequest);
		}catch(TradeValidationException tve ) {
			Assert.assertNotNull(tve);
			Assert.assertEquals(ErrorCode.ERROR_1004, tve.getErrorCode());
			Assert.assertTrue( tve.getErrorList().containsKey(ErrorCode.ERROR_1003));
			
		}
	}
	
	@Test
	public void testDoTradePreValidationFutureMaturityDate() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1L));
		Exception ex = null;
		try {
			tradeValidator.doTradePreValidation(tradeRequest);
		}catch(Exception tve ) {
			ex = tve;
		}
		Assert.assertNull(ex);
	}
	
	@Test
	public void testDoTradePreValidationTodayMaturityDate() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now());
		Exception ex = null;
		try {
			tradeValidator.doTradePreValidation(tradeRequest);
		}catch(Exception tve ) {
			ex = tve;
		}
		Assert.assertNull(ex);
	}
	
	@Test
	public void testUpdateTradeValidationWithWrongVersion() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now());
		try {
			tradeValidator.updateTradeValidation(tradeRequest, 2);
		}catch(TradeValidationException tve ) {
			Assert.assertNotNull(tve);
			Assert.assertEquals(ErrorCode.ERROR_1004, tve.getErrorCode());
			Assert.assertTrue( tve.getErrorList().containsKey (ErrorCode.ERROR_1005));
		}
	}
	
	@Test
	public void testUpdateTradeValidationWithWrongVersionAndDate() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now().minusDays(2L));
		try {
			tradeValidator.updateTradeValidation(tradeRequest, 2);
		}catch(TradeValidationException tve ) {
			Assert.assertNotNull(tve);
			Assert.assertEquals(ErrorCode.ERROR_1004, tve.getErrorCode());
			Assert.assertTrue(tve.getErrorList().containsKey(ErrorCode.ERROR_1005));
			Assert.assertTrue( tve.getErrorList().containsKey(ErrorCode.ERROR_1003));
		}
	}
	
	@Test
	public void testUpdateTradeValidationWithEqualVersion() {
		TradeRequest tradeRequest = new TradeRequest("T1", 1, "CP-1", "B1", LocalDate.now());
		Exception ex = null;
		try {
			tradeValidator.updateTradeValidation(tradeRequest, 1);
		}catch(Exception tve ) {
			ex=tve;
		}
		Assert.assertNull(ex);
	}
	
	@Test
	public void testUpdateTradeValidationWithHigherlVersion() {
		TradeRequest tradeRequest = new TradeRequest("T1", 2, "CP-1", "B1", LocalDate.now());
		Exception ex = null;
		try {
			tradeValidator.updateTradeValidation(tradeRequest, 1);
		}catch(Exception tve ) {
			ex=tve;
		}
		Assert.assertNull(ex);
	}
}
