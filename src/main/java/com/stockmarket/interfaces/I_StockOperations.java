package com.stockmarket.interfaces;

import com.stockmarket.enums.constants.E_Type;

public interface I_StockOperations {
	public double dividendYield(E_Type type, double lastDividend, double tickerPrice, double parValue);
	public double peRatio(double lastDividend, double tickerPrice);
}
