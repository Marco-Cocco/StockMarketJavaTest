package com.stockmarket.entities.collection;

import java.io.Serializable;

import com.stockmarket.enums.constants.E_Type;

public class SimpleStock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stockSymbol;
	private E_Type type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;

	public SimpleStock(
			String stockSymbol
			, E_Type type
			, double lastDividend
			, double fixedDividend
			, double parValue) {
		super();
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public E_Type getType() {
		return type;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}


}
