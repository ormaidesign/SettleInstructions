package com.jpmorgan.test;

public class FileCheckContentSeparatedWithSemiolon extends FileLinesAbstract {
	protected String[] cells = null;
	
	@Override
	public void readLine(String line) {
		cells = line.split(";");
	}

	@Override
	public boolean returnAfterLines() {
		return cells != null && cells.length > 2;
	}
	
	@Override
	public String getErrorStr() {
		return "Not separated format!";
	}
}
