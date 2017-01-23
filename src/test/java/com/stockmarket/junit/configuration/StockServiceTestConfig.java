package com.stockmarket.junit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.entities.collection.StockGlobalMarketOperations;
import com.stockmarket.factories.SimpleStockFactory;
import com.stockmarket.interfaces.I_StockGlobalMarketOperations;
import com.stockmarket.junit.concurrency.RandomTrade;
import com.stockmarket.junit.concurrency.StockMarketPollingService;
import com.stockmarket.junit.enums.constants.TestConstants;

@Configuration 
@Import(UtilsTest.class)
public class StockServiceTestConfig {
	
	@Autowired
	UtilsTest utilsTest;
	
	@Bean
	ConcurrentHashMap<String, SimpleTrade> tradeMap(){
		return new ConcurrentHashMap<String, SimpleTrade>();
	}

	@Bean
	StockMarketPollingService stockMarketPollingService(ConcurrentHashMap<String, SimpleTrade> tradeMap){
		return new StockMarketPollingService(tradeMap);
	}

	@Bean
	I_StockGlobalMarketOperations stockGlobalMarketOperations(){
		return new StockGlobalMarketOperations();
	}

	@Bean
	CountDownLatch countDownLatch(){
		return new CountDownLatch(TestConstants.NUMBER_OF_TASKS);
	}

	@Bean
	SimpleStockFactory simpleStockFactory(){
		return new SimpleStockFactory();
	} 

	@Bean
	Collection<RandomTrade> createRandomTrades(CountDownLatch countDownLatch, SimpleStockFactory simpleStockFactory, ConcurrentHashMap<String, SimpleTrade> tradeMap){
		List<RandomTrade> randomTradesList = new ArrayList<RandomTrade>(); 
		for(int a = 0; a<TestConstants.NUMBER_OF_TASKS; a++)
			randomTradesList.add( new RandomTrade(countDownLatch
					, simpleStockFactory.getSimpleTrade(utilsTest.getRandomDoubleInRange(TestConstants.fromPriceTrade, TestConstants.toPriceTrade) 
							, utilsTest.getRandomDoubleInRange(TestConstants.fromQtyTrade, TestConstants.toQtyTrade)
							, utilsTest.getRandomStock(simpleStockFactory, null)
							, utilsTest.getRandomTradeIndicator()
							)
					, tradeMap));
		return randomTradesList; 
	}
   

}