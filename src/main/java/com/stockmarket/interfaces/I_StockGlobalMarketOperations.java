package com.stockmarket.interfaces;

import com.stockmarket.entities.collection.SimpleTrade;

public interface I_StockGlobalMarketOperations {
	public double stockPrice(SimpleTrade... simpleTrades);
	public double geometricMean(SimpleTrade... stockPrices);
}
