package com.jpmorgan.test;

import java.math.BigDecimal;

public class FileCheckAllPricePerUnit005to1000000 extends FileCellBDAbstract {

	@Override
	public String getErrorStr() {
		return "Price Per Unit error in %1$d line";
	}
	
	@Override
	public BigDecimal getMax() {
		return BigDecimal.valueOf(1000000);
	}

	@Override
	public BigDecimal getMin() {
		return BigDecimal.valueOf(0.05);
	}

	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.PPU;
	}

}
