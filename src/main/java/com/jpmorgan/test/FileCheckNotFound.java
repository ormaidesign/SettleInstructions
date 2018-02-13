package com.jpmorgan.test;

import java.io.File;

public class FileCheckNotFound implements FileCheckerService {

	public boolean check(File f) throws Exception {
		try {
			if (f.exists()) 
				return true;
			else
				throw new Exception(getErrorStr());
		} catch (Exception ex) {
			throw new Exception(getErrorStr(),ex);
		}
	}

	@Override
	public String getErrorStr() {
		return "No instructions file!";
	}

}
