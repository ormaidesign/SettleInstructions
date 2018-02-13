package com.jpmorgan.test;

public class FileCheckFoundCountLines extends FileLinesAbstract {
	private int lineCnt = 0;
	
	@Override
	public void readLine(String line) {
		lineCnt++;
	}

	@Override
	public boolean returnAfterLines() {
		return lineCnt > 0;
	}

}
