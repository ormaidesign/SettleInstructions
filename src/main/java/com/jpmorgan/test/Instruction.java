package com.jpmorgan.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Instruction {
	/**
	 * A financial entity whose shares are to be bought or sold
	 */
	private String Entity;
	/**
	 * Date on which the instruction was sent to JP Morgan by various clients
	 */
	private LocalDate instructionDate; 
	/**
	 * The date on which the client wished for the instruction to be settled with respect to Instruction Date
	 */
	private LocalDate settlementDate;
	/**
	 * Buy/Sell flag:
	 * o B – Buy – outgoing
	 * o S – Sell – incoming
	 * 
	 */
	private BuySell buySell;   
	/**
	 * Agreed Fx is the foreign exchange rate with respect to USD that was agreed
	 */
	private BigDecimal agreedFx;
	private CurrencyEum currency;
	/**
	 * Units: Number of shares to be bought or sold
	 */
	private int units;
	/**
	 * Price Per Unit
	 */
	private BigDecimal ppu;
	/**
	 * Total price in USD to agregate
	 */
	private BigDecimal usd;
	
	public String getEntity() {
		return Entity;
	}
	public void setEntity(String entity) {
		Entity = entity;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}
	public CurrencyEum getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyEum currency) {
		this.currency = currency;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getPpu() {
		return ppu;
	}
	public void setPpu(BigDecimal ppu) {
		this.ppu = ppu;
	}
	public BigDecimal getUsd() {
		return usd;
	}
	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	} 
	
	@Override
	public String toString() {
		return (new StringBuilder()).append(getEntity()).append(";").append(getBuySell()).append(";").append(getAgreedFx()).append(";")
				.append(getInstructionDate()).append(";").append(getSettlementDate()).append(";").append(getUnits()).append(";").append(getPpu()).toString();
	}
}
