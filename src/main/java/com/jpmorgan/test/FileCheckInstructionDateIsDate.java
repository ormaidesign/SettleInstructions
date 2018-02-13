package com.jpmorgan.test;

public class FileCheckInstructionDateIsDate extends FileCellDateAbstract {
	
	@Override
	public String getErrorStr() {
		return "Instruction date error in ? line";
	}
	
	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.INSTRUCTION_DATE;
	}

}
