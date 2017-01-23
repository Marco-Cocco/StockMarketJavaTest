package com.stockmarket.security.constraints;

import com.stockmarket.entities.collection.SimpleStock;

public final class SecurityCheckCollection {
	
	
	public static boolean checkTrade(double tradePrice, double quantity, SimpleStock simpleStock){
		
		if(simpleStock==null)
			return false;
		else if(quantity <=0)
			return false;
		else if(tradePrice<=0)
			return false;
		return true;
	}
	
	public static boolean checkStock(String stockSymbol ){
		if(stockSymbol==null||stockSymbol.equalsIgnoreCase(""))
			return false;
		return true;
	}
	
	 

}
