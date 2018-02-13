package com.jpmorgan.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileCellDateAbstract extends FileCellAbstract {
	private static Logger logger = Logger.getLogger(FileCellDateAbstract.class.getName());
	
	@Override
	public boolean returnAfterLines() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.UK);
			return getCell() != null && LocalDate.parse(getCell(), formatter).getYear() > 2000;
		} catch (Exception ex) {
			logger.log(Level.FINE, "Parse error: "+ex, ex);
			return false;
		}
	}

}
