package com.stockmarket.junit.test.collection;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.junit.concurrency.RandomTrade;
import com.stockmarket.junit.concurrency.StockMarketPollingService;
import com.stockmarket.junit.configuration.StockServiceTestConfig;
import com.stockmarket.junit.enums.constants.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes = {StockServiceTestConfig.class})
public class SimpleStockMarketSimulatorTest {

	private Logger simpleStockMarketSimulatorTestLogger = Logger.getLogger(SimpleStockMarketSimulatorTest.class.getName());
	 
	@Autowired
	private Collection<RandomTrade> createRandomTrades;
	
	@Autowired
	private CountDownLatch countDownLatch;
	
	@Autowired
	private StockMarketPollingService stockMarketPollingService;
	
	@Autowired
	private ConcurrentHashMap<String, SimpleTrade> tradeMap;
  
	private  ExecutorService executorStockService; 
	private  ScheduledFuture<?> scheduledFuture;
	private  ScheduledExecutorService scheduledExecutorService;
 
	@Before 
	public void prepareTest(){  
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		
		scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(stockMarketPollingService, 0, TestConstants.sleepPollingTimeValue, TestConstants.sleepPollingMessageTimeUnit);

		executorStockService = Executors.newFixedThreadPool(TestConstants.fixedThreadPoolCount);
		
		simpleStockMarketSimulatorTestLogger.info("Hey folks! The Simple-Stock-Market has just opened!!! (^-^)");
	}
  
	@Test
	public void stockMarketSimulator() throws InterruptedException{ 
 
		for(RandomTrade collectionTrades : createRandomTrades ) 
			executorStockService.execute(collectionTrades); 

		System.out.println("TradeMap Size: " + tradeMap.size() + " ...now waiting countdownlatch expiration ok...");

		countDownLatch.await();

		System.out.println("Final TradeMap Size: " + tradeMap.size() + " ...countdownlatch expiry arrived!"); 
	}

	@After
	public void cleanTest(){
 
		scheduledFuture.cancel(true);
		scheduledExecutorService.shutdown(); 
		executorStockService.shutdown();
		
		simpleStockMarketSimulatorTestLogger.info("Ladies and Gentleman: Simple-Stock-Market has just closed... (-_-)°");
	} 
}
