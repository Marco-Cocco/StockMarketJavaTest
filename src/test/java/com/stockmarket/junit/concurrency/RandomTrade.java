package com.stockmarket.junit.concurrency;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.junit.enums.constants.TestConstants;


public class RandomTrade implements Runnable{
  
	private CountDownLatch countDownLatch; 
	 
	private SimpleTrade simpleTrade;
	private ConcurrentHashMap<String, SimpleTrade> tradeMap;
	
	public RandomTrade(CountDownLatch countDownLatch, SimpleTrade simpleTrade, ConcurrentHashMap<String, SimpleTrade> tradeMap){  
		this.countDownLatch = countDownLatch;
		this.simpleTrade = simpleTrade;
		this.tradeMap = tradeMap;
	}

	@Override
	public void run() { 

		try {
			Thread.sleep(getRandomQuantityInRange(TestConstants.fromSleepingTrade, TestConstants.toSleepingTrade));
		} catch (InterruptedException e) { 
			e.printStackTrace();
		} 
		 
		System.out.println(simpleTrade.toString());
		
		tradeMap.put(Thread.currentThread().getName() + "-" + new Date().getTime(), simpleTrade);
		 
		countDownLatch.countDown(); 
	}

	private int getRandomQuantityInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	} 
	
}
