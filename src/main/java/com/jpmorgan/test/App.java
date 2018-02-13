package com.jpmorgan.test;

import java.io.File;

public class App {
    public static void main(String[] args ) {
    	if (args.length != 1) {
    		System.err.println("Parameter error! File name not found!");
    		System.exit(1);
    	}
    	Settler s = new Settler(new File(args[0]));
    	System.out.println("\nAmount in USD settled incoming everyday");
    	Reporter.reportAmUSD(s.getInstructions(),BuySell.S).stream().forEach(System.out::println);
    	System.out.println("\nAmount in USD settled outgoing everyday");
    	Reporter.reportAmUSD(s.getInstructions(),BuySell.B).stream().forEach(System.out::println);
    	System.out.println("\nRanking of entities based on outgoing amount");
    	Reporter.reportRank(s.getInstructions(), BuySell.B).stream().forEach(System.out::println);
    	System.out.println("\nRanking of entities based on incoming amount");
    	Reporter.reportRank(s.getInstructions(), BuySell.S).stream().forEach(System.out::println);
    }
}
