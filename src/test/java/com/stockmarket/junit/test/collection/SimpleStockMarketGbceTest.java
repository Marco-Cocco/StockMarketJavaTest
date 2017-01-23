package com.stockmarket.junit.test.collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.stockmarket.entities.collection.SimpleStock;
import com.stockmarket.entities.collection.SimpleTrade;
import com.stockmarket.entities.collection.StockGlobalMarketOperations;
import com.stockmarket.factories.SimpleStockFactory;
import com.stockmarket.junit.configuration.StockServiceTestConfig;
import com.stockmarket.junit.configuration.UtilsTest;
import com.stockmarket.junit.enums.constants.E_StockSymbols;
import com.stockmarket.junit.enums.constants.TestConstants;

@RunWith(Parameterized.class) 
@ContextConfiguration(classes = {StockServiceTestConfig.class, UtilsTest.class}) 
public   class SimpleStockMarketGbceTest {

	@Before
	public void setUp() throws Exception {
		testContextManager = new TestContextManager(this.getClass());
		testContextManager.prepareTestInstance(this); 
	}

	private static TestContextManager testContextManager;

	private static List<SimpleTrade> tradeList = new ArrayList<SimpleTrade>();

	@Parameter
	public E_StockSymbols domainName;

	@Parameters( name = "{index}: Stock Test({0})" )
	public static Object[] data() {
		return E_StockSymbols.values(); 
	}

	@Test
	public void validNameTest() {  
		assertNotNull(domainName.name());
	}

	@Test
	public void validStockTest() {  
		SimpleStockFactory simpleStockFactory = testContextManager.getTestContext().getApplicationContext().getBean("simpleStockFactory",SimpleStockFactory.class);
		UtilsTest utilsTest = testContextManager.getTestContext().getApplicationContext().getBean("utilsTest",UtilsTest.class);
		SimpleStock simpleStock = utilsTest.getRandomStock(simpleStockFactory, domainName);
		assertNotNull(simpleStock);  
		SimpleTrade simpleTrade = simpleStockFactory.getSimpleTrade(utilsTest.getRandomDoubleInRange(TestConstants.fromPriceTrade, TestConstants.toPriceTrade) 
				, utilsTest.getRandomDoubleInRange(TestConstants.fromQtyTrade, TestConstants.toQtyTrade)
				, simpleStock
				, utilsTest.getRandomTradeIndicator());
		assertNotNull(simpleTrade);  
		tradeList.add(simpleTrade); 
	}

	@Test
	public void calculateGBCETest() {  
		StockGlobalMarketOperations stockGlobalMarketOperations 
		= testContextManager.getTestContext().getApplicationContext().getBean("stockGlobalMarketOperations",StockGlobalMarketOperations.class);

		assertNotNull(tradeList);  
		int sizeList = tradeList.size();

		if(sizeList==E_StockSymbols.values().length-1){
			double finalGbceDouble = stockGlobalMarketOperations.geometricMean(tradeList.toArray(new SimpleTrade[sizeList]));
			
			System.out.println("finalGbceDouble: " + finalGbceDouble);
			
			assertTrue(finalGbceDouble>0); 
		}
	}

	@After
	public void closeTest() throws Exception {
		testContextManager = null; 
	}
} 
