package com.jpmorgan.test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Settler {
	private static Logger logger = Logger.getLogger(Settler.class.getName());
	
	private File file;
	private static List<Instruction> instructions = new ArrayList<>();
	private static final List<FileCheckerService> fileCheckers = new ArrayList<FileCheckerService>() {{
		add(new FileCheckNotFound());
		add(new FileCheckFoundCountLines());
		add(new FileCheckContentSeparatedWithSemiolon());
		add(new FileCheckHas8Column());
		add(new FileCheckTrimmedEntityLength1to100());
		add(new FileCheckAgreedFx0to1000());
		add(new FileCheckInstructionDateIsDate());
		add(new FileCheckSettlementDateIsDate());
		add(new FileCheckAllUnits1to1000000int());
		add(new FileCheckAllPricePerUnit005to1000000());
	}};
	
	public Settler(File f) {
		this.file = f;
		checkFile();
		processLines();
	}
	
	public static boolean isSettlementDateInWorkWeek(LocalDate settlementDate, String currency) {
		return Arrays.stream(CurrencyEum.valueOf(currency).getWorkdays())
        .filter(x -> settlementDate.getDayOfWeek().toString().equals(x))
        .findFirst().isPresent();
	}
	
	public static LocalDate findNextSettlementDate(LocalDate settlementDate, String currency) {
		if (isSettlementDateInWorkWeek(settlementDate, currency))
			return settlementDate;
    return settlementDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(CurrencyEum.valueOf(currency).getWorkdays()[0])));
	}
	
	public static BigDecimal getUSD(Instruction i) {
		return getUSD(i.getPpu(), i.getUnits(), i.getAgreedFx());
	}
	
	public static BigDecimal getUSD(BigDecimal pricePerUnit, int units,  BigDecimal agreedFx) {
		return agreedFx.multiply(pricePerUnit).multiply(BigDecimal.valueOf(units)).setScale(2, RoundingMode.HALF_UP);
	}
	
	public static void normalizeInstruction(Instruction i) {
		i.setSettlementDate(findNextSettlementDate(i.getSettlementDate(), i.getCurrency().toString()));
		i.setUsd(getUSD(i));
	}
	
	public void checkFile() {
		fileCheckers.stream().forEach(c->{
			try {
				c.check(file);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	public void processLines() {
		try {
			try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
        stream.skip(1).forEach(s-> {
        	Instruction i = InstructionBuilder.toInstruction(s);
        	normalizeInstruction(i);
        	instructions.add(i);
        });
			} 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			logger.log(Level.FINE, ex.getMessage(), ex);
		}
	}

	public static List<Instruction> getInstructions() {
		return instructions;
	}

	public static void setInstructions(List<Instruction> instructions) {
		Settler.instructions = instructions;
	}
	
	
}
