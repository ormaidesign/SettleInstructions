package com.jpmorgan.test;

public class FileCheckHas8Column extends FileCheckContentSeparatedWithSemiolon {
	
	public String getErrorStr() {
		return "Not correct format in %1$d line!";
	}
	
	@Override
	public boolean returnAfterLines() {
		return cells != null && cells.length == 8;
	}

}
