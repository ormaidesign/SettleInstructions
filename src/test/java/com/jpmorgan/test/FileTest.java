package com.jpmorgan.test;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

public class FileTest {
	
	@Rule
  public ExpectedException thrown = ExpectedException.none();
	
	static File testFile;
	
	@BeforeClass
	public static void init(){
	    testFile = new File("test.csv");
	}
	
	private boolean check(FileCheckerService fc, boolean acceptException) {
		try {
    	return fc.check(testFile);
    } catch (Exception ex) {
    	return acceptException && fc.getErrorStr().equals(ex.getMessage());
    }
	}
	
	@Test
	public void whenFileNotFoundPrint() throws Exception {
//    thrown.expect(Exception.class);
//    thrown.expectMessage("No instructions file!");
//    FileCheckerService fc = new FileCheckNotFound();

		assert(check(new FileCheckNotFound(),false));
  }
	@Test
  public void whenFileFoundCountLines() {
		assert(check(new FileCheckFoundCountLines(),false));
  }
	@Test
  public void whenFileContentSeparatedWithSemiolon() { 
		assert(check(new FileCheckContentSeparatedWithSemiolon(),false));
  }
	@Test
  public void whenFileHas8Column() { 
		assert(check(new FileCheckHas8Column(),false));
  }
	@Test
  public void whenFileAllTruncatedEntityLength1to100() { 
  	assert(check(new FileCheckTrimmedEntityLength1to100(), false));
  }
	@Test
  public void whenFileAllAgreedFx0to1000() {
  	assert(check(new FileCheckAgreedFx0to1000(), false));
  }
	@Test
  public void whenFileAllInstructionDateIsDate() { 
  	assert(check(new FileCheckInstructionDateIsDate(), false));
  }
	@Test
  public void whenFileAllSettlementDateIsDate() {
  	assert(check(new FileCheckSettlementDateIsDate(), false));
  }
	@Test
  public void whenFileAllUnits1to1000000int() {
  	assert(check(new FileCheckAllUnits1to1000000int(),false));
  }
	@Test
  public void whenFileAllPricePerUnit005to1000000() {
  	assert(check(new FileCheckAllPricePerUnit005to1000000(), false));
  }
}
