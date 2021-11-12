/**
 * 
 */
package com.demo.tradestore.validators;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.demo.tradestore.exception.TradeValidationException;
import com.demo.tradestore.exception.errorcode.ErrorCode;
import com.demo.tradestore.request.TradeRequest;

/**
 * @author Aniruddh Mishra
 *
 */
@Component
public class TradeValidator {

	public void doTradePreValidation(TradeRequest tradeRequest) {
		Map<ErrorCode, String> validationErrors = new HashMap<>(0);

		this.validateMaturityDate(tradeRequest.getMaturityDate(), validationErrors);

		if (!validationErrors.isEmpty()) {

			throw new TradeValidationException(ErrorCode.ERROR_1004, validationErrors,
					new RuntimeException("Trade Validations Failed"));
		}
	}

	private void validateMaturityDate(LocalDate maturityDate, Map<ErrorCode, String> validationErrors) {
		if (maturityDate.isBefore(LocalDate.now())) {
			validationErrors.put(ErrorCode.ERROR_1003, ErrorCode.ERROR_1003.getErrorMessage());
		}

	}

	private void validateTradeVersion(Integer existingTradeVersion, Integer requestedTradeVersion,
			Map<ErrorCode, String> validationErrors) {
		if (requestedTradeVersion < existingTradeVersion) {
			validationErrors.put(ErrorCode.ERROR_1005, ErrorCode.ERROR_1005.getErrorMessage());
		}
	}

	public void updateTradeValidation(TradeRequest tradeRequest, Integer existingTradeVersion) {

		Map<ErrorCode, String> validationErrors = new HashMap<>(0);

		this.validateMaturityDate(tradeRequest.getMaturityDate(), validationErrors);
		this.validateTradeVersion(existingTradeVersion, tradeRequest.getVersion(), validationErrors);

		if (!validationErrors.isEmpty()) {

			throw new TradeValidationException(ErrorCode.ERROR_1004, validationErrors,
					new RuntimeException("Trade Validations Failed"));
		}

	}
}
