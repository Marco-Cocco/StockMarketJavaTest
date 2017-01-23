package com.stockmarket.entities.collection;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.stockmarket.abstracts.SimpleTradeAbstract;
import com.stockmarket.enums.constants.Constants;
import com.stockmarket.enums.constants.E_TradeIndicator;
import com.stockmarket.enums.constants.E_Type;

public class SimpleTrade extends SimpleTradeAbstract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Identity Properties
	 */  
	private double quantity = 0.0; 
	private E_TradeIndicator tradeIndicator; 
	private double tradePrice = 0.0;
	private Date tradeTime;

	public SimpleTrade(double tradePrice
			, double quantity
			, SimpleStock simpleStock
			, E_TradeIndicator tradeIndicator) {
		super(simpleStock);  
		
		this.quantity = quantity; 
		this.tradeIndicator = tradeIndicator;
		this.tradeTime = new Date(); 
		this.tradePrice = tradePrice;
		
		if((simpleStock.getType().equals(E_Type.COMMON) && simpleStock.getLastDividend() > 0.0)
				||(simpleStock.getType().equals(E_Type.PREFERRED) && simpleStock.getFixedDividend() > 0.0))
			super.dividendYield = dividendYield(simpleStock.getType(), simpleStock.getLastDividend(), tradePrice, simpleStock.getParValue());
		
		if(simpleStock.getLastDividend() > 0.0)
			super.peRatio = peRatio(simpleStock.getLastDividend(), tradePrice);
	}


	public E_TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	} 

	public Date getTradeTime() {
		return tradeTime;
	}

	public double getTradePrice() {
		return tradePrice;
	}
	 
	public double getQuantity() {
		return quantity;
	}

	public synchronized double dividendYield(E_Type type, double lastDividend, double tickerPrice, double parValue) {
		
		double result = 0;
		
		 switch(type){
			 case COMMON:
				 result =  lastDividend/tickerPrice;
				 break;
			 case PREFERRED:
				 result = (lastDividend*parValue)/tickerPrice;
				 break;
		 }   
		 
		 return result;
	}

	public synchronized double peRatio(double lastDividend, double tickerPrice) { 
		return tickerPrice/lastDividend;
	}


	@Override
	public String toString(){
		return "Trade Generated -"
				+ " Time: " +  new SimpleDateFormat(Constants.dateFormat).format(getTradeTime()) 
				+ " Stock-Symbol: " + simpleStock.getStockSymbol()
				+ " Price: " +  getTradePrice() 
				+ " Q.ty: " +  getQuantity()
				+ " Action: " +  getTradeIndicator().name()
				+ " Div.Yield: " + getDividendYield()  
				+ " P/E-Rt: " +  getPeRatio()
				;
	}

}
