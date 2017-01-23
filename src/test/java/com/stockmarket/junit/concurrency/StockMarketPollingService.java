package com.stockmarket.junit.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.interfaces.I_StockGlobalMarketOperations;
import com.stockmarket.junit.enums.constants.TestConstants;



@Component
public class StockMarketPollingService implements Runnable {

	private Logger StockMarketPollingServiceLogger = Logger.getLogger(StockMarketPollingService.class.getName());

	private ConcurrentHashMap<String, SimpleTrade> tradeMap;

	public StockMarketPollingService(ConcurrentHashMap<String, SimpleTrade> tradeMap){
		this.tradeMap = tradeMap;
	}

	@Autowired
	private I_StockGlobalMarketOperations stockGlobalMarketOperations;

	public void run() {
		 
		int size  = tradeMap.size();  
		
		if(size>0){ 
			StockMarketPollingServiceLogger.info("StockMarketPoll Live ADVERTISING - Global Stock Price (Last " 
					+ TestConstants.sleepPollingTimeValue
					+ " "
					+ TestConstants.sleepPollingMessageTimeUnit.name()
					+ ") :  "   
					+ stockGlobalMarketOperations.stockPrice(tradeMap.values().toArray(new SimpleTrade[size]))
					);  

			tradeMap.clear(); 
		}
	} 
}