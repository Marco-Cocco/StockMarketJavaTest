package com.stockmarket.abstracts;

import com.stockmarket.entities.collection.SimpleStock;
import com.stockmarket.interfaces.I_StockOperations;

public abstract class SimpleTradeAbstract implements I_StockOperations{
	
	/*
	 * Stored SimpleStock object
	 */
	protected SimpleStock simpleStock;
	
	/*
	 * Stored Calculated Properties
	 */
	protected double dividendYield = 0.0;
	protected double peRatio = 0.0;
 
	public SimpleTradeAbstract(SimpleStock simpleStock){
		this. simpleStock = simpleStock; 
	}
	
	public SimpleStock getSimpleStock() {
		return simpleStock;
	}
	

	public double getDividendYield() {
		return dividendYield;
	}

	public double getPeRatio() {
		return peRatio;
	}


}
