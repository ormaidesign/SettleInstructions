package com.jpmorgan.test;

public class FileCheckSettlementDateIsDate extends FileCellDateAbstract {
	
	@Override
	public String getErrorStr() {
		return "Settlement date error in %1$d line";
	}
	
	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.SETTLEMENT_DATE;
	}

}
