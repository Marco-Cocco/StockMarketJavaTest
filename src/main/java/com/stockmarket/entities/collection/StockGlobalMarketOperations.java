package com.stockmarket.entities.collection;

import java.util.ArrayList;
import java.util.List;

import com.stockmarket.interfaces.I_StockGlobalMarketOperations;

public final class StockGlobalMarketOperations implements I_StockGlobalMarketOperations{
	
	public synchronized double stockPrice(SimpleTrade... simpleTrades) {

		double simpleTradesQtySum = 0;
		double simpleTradesPrice4QtySum = 0;

		for(SimpleTrade simpleTrade : simpleTrades){
			simpleTradesPrice4QtySum += simpleTrade.getQuantity()*simpleTrade.getTradePrice();
			simpleTradesQtySum += simpleTrade.getQuantity();
		}

		return simpleTradesPrice4QtySum/simpleTradesQtySum;
	}

	public synchronized double geometricMean(SimpleTrade... stockPrices) {

		List<Double> tradesPrices = new ArrayList<Double>();
		for(SimpleTrade part : stockPrices) 
			tradesPrices.add(part.getTradePrice()); 
		
		return  Math.pow(calculateGlobalProduct(tradesPrices), 1.0/tradesPrices.size()); 
	}
	  
	private double calculateGlobalProduct(List<Double> dob){ 
		double result = 0;
		for(double d : dob){
			if(result==0)
				result = d;
			else
				result = result*d; 
		}
		return result;
	}
}
