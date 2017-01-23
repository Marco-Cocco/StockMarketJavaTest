package com.stockmarket.factories;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.stockmarket.entities.collection.SimpleStock;
import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.enums.constants.E_TradeIndicator;
import com.stockmarket.enums.constants.E_Type;
import com.stockmarket.security.constraints.SecurityCheckCollection;


@Component
public class SimpleStockFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public synchronized SimpleStock getSimpleStock(String stockSymbol
			, E_Type type
			, double lastDividend
			, double fixedDividend
			, double parValue){
		
		if(!SecurityCheckCollection.checkStock(stockSymbol))
			return null;
		
		return new SimpleStock(stockSymbol
				,  type
				,  lastDividend
				,  fixedDividend
				,  parValue);
	}
	
	public synchronized SimpleTrade getSimpleTrade(
			double tradePrice
			, double quantity
			, SimpleStock simpleStock
			, E_TradeIndicator tradeIndicator){ 
		
		if(!SecurityCheckCollection.checkTrade(tradePrice, quantity, simpleStock))
			return null;
		
		return new SimpleTrade(tradePrice, quantity, simpleStock, tradeIndicator);
	}
	 
}
