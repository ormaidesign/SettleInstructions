package com.jpmorgan.test;

public class FileCheckTrimmedEntityLength1to100 extends FileCellAbstract {
	
	@Override
	public String getErrorStr() {
		return "Entity error in %1$d line!";
	}
	
	@Override
	public FileCellEnum getCellType() {
		return FileCellEnum.ENTITY;
	}

	@Override
	public boolean returnAfterLines() {
		return getCell() != null && getCell().trim().length() > 0 && getCell().trim().length() <= 100;
	}

}
