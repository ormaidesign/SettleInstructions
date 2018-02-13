package com.jpmorgan.test;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Reporter {
	
	public static List<String> reportAmUSD(List<Instruction> list, BuySell buySell) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.UK);
		return list.stream()
		.filter(i->i.getBuySell().compareTo(buySell) == 0)
		 .collect(Collectors.groupingBy(
         Instruction::getSettlementDate,
         Collectors.mapping(
                 Instruction::getUsd,
                 Collectors.reducing(
                         BigDecimal.ZERO,
                         (sum, elem) -> sum.add(elem)))))
		 .entrySet().stream()
		 .sorted((e1,e2)->e1.getKey().compareTo(e2.getKey()))
		 .map(e->(new StringBuilder()).append(e.getKey().format(formatter)).append("\t").append(e.getValue()).toString())
		 .collect(Collectors.toList())
			;	
	}
	
	public static List<String> reportAmUSDIn(List<Instruction> list) {
		return reportAmUSD(list, BuySell.S);
	}
	
	public static List<String> reportAmUSDOut(List<Instruction> list) {
		return reportAmUSD(list, BuySell.B);
	}
	
	
	public static List<String> reportRank(List<Instruction> list, BuySell buySell) {
		return list.stream()
				.filter(i->i.getBuySell().compareTo(buySell) == 0)
				.collect(Collectors.groupingBy(
						Instruction::getEntity,
						Collectors.mapping( Instruction::getUsd, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
						))
		.entrySet().stream().sorted((i1,i2)->i1.getValue().compareTo(i2.getValue())*-1)
		.map(e-> (new StringBuilder())
				.append(e.getKey()).append("\t")
				.append(e.getValue())
				.toString()).collect(Collectors.toList());
	}
	
	
}
