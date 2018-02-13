package com.jpmorgan.test;

public enum FileCellEnum {
	ENTITY(0, "Entity"), BUY_SELL(1, "Buy/Sell"), AGREED_FX(2, "AgreedFx"), CURRENCY(3, "Currency"), 
	INSTRUCTION_DATE(4, "InstructionDate"), SETTLEMENT_DATE(5, "SettlementDate"), UNITS(6, "Units"), 
	PPU(7, "Price per unit");

	private final int key;
	private final String value;

	FileCellEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}
}
