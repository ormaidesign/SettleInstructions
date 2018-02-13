package com.jpmorgan.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class FileLinesAbstract implements FileCheckerService {

	@Override
	public String getErrorStr() {
		return "Data file read error!";
	}
	
	@Override
	public boolean check(File f) throws Exception {
		readLinesOfFile(f);
		if (!returnAfterLines()) {
			throw new Exception(getErrorStr());
		}
		return true;
	}
	
	public void readLinesOfFile(File f) throws Exception {
		try {
			try (Stream<String> stream = Files.lines(Paths.get(f.toURI()))) {
        stream.forEach(s-> readLine(s));
			} 
		} catch (Exception ex) {
			throw new Exception(getErrorStr(),ex);
		}	
	}
	
	public abstract void readLine(String line);
	public abstract boolean returnAfterLines();
}
