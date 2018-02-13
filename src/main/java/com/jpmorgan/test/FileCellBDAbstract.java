package com.jpmorgan.test;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileCellBDAbstract extends FileCellAbstract {
	private static Logger logger = Logger.getLogger(FileCheckAgreedFx0to1000.class.getName());
	
	public abstract BigDecimal getMax();
	public abstract BigDecimal getMin();

	@Override
	public boolean returnAfterLines() {
		try {
			return getCell() != null && getMin().compareTo(new BigDecimal(getCell())) <= 0 && getMax().compareTo(new BigDecimal(getCell())) >= 0;
		} catch (Exception ex) {
			logger.log(Level.FINE, "Parse error: "+ex, ex);
			return false;
		}
	}
}
