package com.jpmorgan.test;

import java.math.BigDecimal;

public class FileCheckAgreedFx0to1000 extends FileCellBDAbstract {
	
	@Override
	public String getErrorStr() {
		return "AgreedFx error in %1$d line";
	}
	
	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.AGREED_FX;
	}

	@Override
	public BigDecimal getMax() {
		return BigDecimal.valueOf(1000);
	}

	@Override
	public BigDecimal getMin() {
		return BigDecimal.ZERO;
	}
}
