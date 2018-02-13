package com.jpmorgan.test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCheckAllUnits1to1000000int extends FileCellAbstract {
	private static Logger logger = Logger.getLogger(FileCheckAllUnits1to1000000int.class.getName());
	
	@Override
	public String getErrorStr() {
		return "Units error in %1$d line";
	}
	
	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.UNITS;
	}

	@Override
	public boolean returnAfterLines() {
		try {
			return getCell() != null && Integer.parseInt(getCell()) >= 0 && Integer.parseInt(getCell()) <= 1000;
		} catch (Exception ex) {
			logger.log(Level.FINE, "Parse error: "+ex, ex);
			return false;
		}
	}

}
