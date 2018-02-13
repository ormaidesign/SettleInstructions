package com.jpmorgan.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SettlerTest {
	@Test
	public void whenSettlementDateMondaySGPThenTrue() {
  	assert(Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("MONDAY"))) , "SGP"));
  }
	@Test
  public void whenSettlementDateFridaySGPThenTrue() {
		assert(Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("FRIDAY"))) , "SGP"));
  }
	@Test
  public void whenSettlementDateSaturdaySGPThenFalse() {
		assert(!Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("SATURDAY"))) , "SGP"));
  }
	@Test
  public void whenSettlementDateSundaySGPThenFalse() {
		assert(!Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("SUNDAY"))) , "SGP"));
  }
	@Test
  public void whenSettlementDateSundayAEDThenTrue() {
		assert(Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("SUNDAY"))) , "AED"));
  }
	@Test
  public void whenSettlementDateThursdayAEDThenTrue() {
		assert(Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("THURSDAY"))) , "AED"));
  }
	@Test
  public void whenSettlementDateFridayAEDThenFalse() {
		assert(!Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("FRIDAY"))) , "AED"));
  }
	@Test
  public void whenSettlementDateSaturdayAEDThenFalse() {
		assert(!Settler.isSettlementDateInWorkWeek(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("SATURDAY"))) , "AED"));
  }
	@Test
  public void whenSettlementDateFridaySGPThenNotChange() {
		LocalDate d = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
  	assert(d.compareTo(Settler.findNextSettlementDate(d, "SGP")) == 0);
  }
	@Test
  public void whenSettlementDateSaturdaySGPThenMonday() {
		LocalDate d = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		LocalDate d1 = d.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
  	assert(d1.compareTo(Settler.findNextSettlementDate(d, "SGP")) == 0);
  }
	@Test
  public void whenSettlementDateThursdayAEDThenNotChange() {
		LocalDate d = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
  	assert(d.compareTo(Settler.findNextSettlementDate(d, "AED")) == 0);
  }
	@Test
  public void whenSettlementDateFridayAEDThenSunday() {
		LocalDate d = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.valueOf("FRIDAY")));
		LocalDate d1 = d.with(TemporalAdjusters.next(DayOfWeek.valueOf("SUNDAY")));
  	assert(d1.compareTo(Settler.findNextSettlementDate(d, "AED")) == 0);
  }
	@Test
  public void whenPPUIs124p35UnitsIs311AgreedFxIs0p44Then17016p05() {
  	assert(BigDecimal.valueOf(17016.05).compareTo(Settler.getUSD(BigDecimal.valueOf(124.35), 311, BigDecimal.valueOf(0.44))) == 0);
  }
}
