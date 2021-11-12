/**
 * 
 */
package com.demo.tradestore.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

/**
 * @author Aniruddh Mishra
 *
 */

@Entity
public class TradeDetails {

	@Id
	@GeneratedValue
	private Long id;

	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private String expired;

	@CreatedDate
	private LocalDate createdDate;
	
	public TradeDetails() {
		super();
	}

	public TradeDetails(TradeDetails tradeDetails, String expired) {
		super();
		this.tradeId = tradeDetails.getTradeId();
		this.version = tradeDetails.getVersion();
		this.counterPartyId = tradeDetails.getCounterPartyId();
		this.bookId = tradeDetails.getBookId();
		this.maturityDate = tradeDetails.getMaturityDate();
		this.createdDate = tradeDetails.getCreatedDate();
		this.expired = expired;
		this.id = tradeDetails.getId();
	}

	public TradeDetails(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate,
			LocalDate createdDate, String expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}
}