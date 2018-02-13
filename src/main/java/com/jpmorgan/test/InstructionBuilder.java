package com.jpmorgan.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstructionBuilder {
	private static Logger logger = Logger.getLogger(InstructionBuilder.class.getName());
	
	public static Instruction toInstruction(String line) {
		String[] cells = line.split(";");
		Instruction out = new Instruction();
		out.setEntity(cells[FileCellEnum.ENTITY.getKey()]);
		out.setBuySell(BuySell.valueOf(cells[FileCellEnum.BUY_SELL.getKey()]));
		out.setAgreedFx(new BigDecimal(cells[FileCellEnum.AGREED_FX.getKey()]));
		out.setCurrency(CurrencyEum.valueOf(cells[FileCellEnum.CURRENCY.getKey()]));
		out.setInstructionDate(toDate(cells[FileCellEnum.INSTRUCTION_DATE.getKey()]));
		out.setSettlementDate(toDate(cells[FileCellEnum.SETTLEMENT_DATE.getKey()]));
		out.setUnits(Integer.parseInt(cells[FileCellEnum.UNITS.getKey()]));
		out.setPpu(new BigDecimal(cells[FileCellEnum.PPU.getKey()]));
		return out;
	}
	
	private static LocalDate toDate(String in) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.UK);
			return LocalDate.parse(in, formatter);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(Level.FINE, "Parse error: "+ex, ex);
			return null;
		}
	}
}
