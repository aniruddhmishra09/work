/**
 * 
 */
package com.demo.tradestore.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.tradestore.model.TradeDetails;

/**
 * @author Aniruddh Mishra
 *
 */

@Repository
public interface TradeDetailsRepository extends JpaRepository<TradeDetails, Integer> {

	List<TradeDetails> findByExpiredAndMaturityDateLessThan(String expired, LocalDate today);

	TradeDetails findByTradeId(String tradeId);

	@Query(value = "SELECT * FROM TRADE_DETAILS where TRADE_ID = :tradeId order by VERSION desc limit 1", nativeQuery = true)
	List<TradeDetails> findTradeDetailsByOrderAndLimit(@Param("tradeId") String tradeId);
}
