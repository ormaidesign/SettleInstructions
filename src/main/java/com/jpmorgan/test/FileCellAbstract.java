package com.jpmorgan.test;

public abstract class FileCellAbstract extends FileLinesAbstract {
	protected String[] cells = null;
	
	@Override
	public void readLine(String line) {
		cells = line.split(";");
	}

	public String getCell() {
		if (cells == null || cells.length != 8) 
			return null;
		return cells[getCellType().getKey()];
	}
	
	public abstract FileCellEnum getCellType();
}
