package com.jpmorgan.test;

import java.io.File;

public interface FileCheckerService {
	public String getErrorStr();
	public boolean check(File f) throws Exception;
}
