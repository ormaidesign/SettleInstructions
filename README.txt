PLEAS RUN
mvn package
java -jar target/SettleInstructions-1.0.jar test.csv



The process structure in nutshell

-import instructions
 -find the file
  +TEST whenFileNotFoundPrint "No instructions file!"
  +TEST whenFileFoundCountLines > 0 "No instructions file data!"
  +TEST whenFileContentSeparatedWithSemiolon "Not separated format!"
  +TEST whenFileHas8Column "Not correct format!"
  +TEST whenFileAllTruncatedEntityLength1to100 "Entity error in ? line!"
  +TEST whenFileAllAgreedFx0to1000 "AgreedFx error in ? line"
  +TEST whenFileAllInstructionDateIsDate "Instruction date error in ? line"
  +TEST whenFileAllSettlementDateIsDate "Settlement date error in ? line"
  +TEST whenFileAllUnits1to1000000int "Units error in ? line"
  +TEST whenFileAllPricePerUnit005to1000000 "Price Per Unit error in ? line"
-settle the instructions
 -process the lines
	-correct settlement day
	 -isSettlementDateInWorkWeek(Date settlementDate, String currency)
	 +TEST whenSettlementDateMondaySGPThenTrue
	 +TEST whenSettlementDateFridaySGPThenTrue
	 +TEST whenSettlementDateSaturdaySGPThenFalse
	 +TEST whenSettlementDateSundaySGPThenFalse
	 +TEST whenSettlementDateSundayAEDThenTrue
	 +TEST whenSettlementDateThursdaySGPThenTrue
	 +TEST whenSettlementDateFridaySGPThenFalse
	 +TEST whenSettlementDateSaturdayAEDThenFalse
	 -findNextSettlementDate(Date settlementDate, String currency)
	 +TEST whenSettlementDateFridaySGPThenNotChange
	 +TEST whenSettlementDateSaturdaySGPThenMonday
	 +TEST whenSettlementDateThursdayAEDThenNotChange
	 +TEST whenSettlementDateFridayAEDThenSunday
	-calculate total price in usd
	 -getUSD(BigDecimal pricePerUnit, int units,  BigDecimal agreedFx)
	 +TEST whenPPUIs124p35UnitsIs311AgreedFxIs0p44Then17016p05
	-create instruction object
	-add to list
-create reports
 -Amount in USD settled incoming everyday
  -reportAmUSDIn(List<Instruction> list)
  +TEST whenList20160107Then14899p5  // TEST instruction + 1 USD
 -Amount in USD settled outgoing everyday
  -reportAmUSDOut(List<Instruction> list)
  +TEST whenList20160102Then11028p5  // TEST instruction + 1 USD
 -Ranking of entities based on incoming and outgoing amount.
  -reportRank(List<Instruction> list)
  +TEST whenInListBULKentityThenAtTop // Add a Big instruction from bulk



Classes

-App
-FileCheckerService
 -FileCheckNotFound
 -FileCheckFoundCountLines
 -FileCheckContentSeparatedWithSemiolon
 -FileCheckHas8Column
 -FileCheckTruncatedEntityLength1to100
 -FileCheckAgreedFx0to1000
 -FileCheckInstructionDateIsDate
 -FileCheckSettlementDateIsDate
 -FileCheckAllUnits1to1000000int
 -FileCheckAllPricePerUnit005to1000000
-Importer
-Settler
-ReportService
 -ReportAmountUSDIn
 -ReportAmountUSDOut
 -ReportRank
 
