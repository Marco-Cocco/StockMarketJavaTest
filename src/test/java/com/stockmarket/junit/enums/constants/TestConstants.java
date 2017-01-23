package com.stockmarket.junit.enums.constants;

import java.util.concurrent.TimeUnit;

public class TestConstants {
	
	public final static int fixedThreadPoolCount = 10;
	
	/*
	 * Test GBCE Timer
	 */
	public final static Long sleepPollingTimeValue = 15L;
	public final static TimeUnit sleepPollingMessageTimeUnit = TimeUnit.MINUTES;
	
	/*
	 * TestMarket number trades
	 */
	public final static int NUMBER_OF_TASKS = 450;
	
	/*
	 * RandomTime: operation's sleeping time range
	 */
	public final static int fromSleepingTrade = 10000;
	public final static int toSleepingTrade = 60000;
	
	/*
	 * RandomPrice: operation's sleeping time range
	 */
	public final static double fromPriceTrade = 0.1;
	public final static double toPriceTrade = 100;
	/*
	 * RandomQty: operation's sleeping time range
	 */
	public final static double fromQtyTrade = 1;
	public final static double toQtyTrade = 100; 
}
