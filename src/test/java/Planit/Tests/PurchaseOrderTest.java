package Planit.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Planit.Pages.CartPage;
import Planit.Pages.HomePage;
import Planit.Pages.ShopPage;
import Planit.Resources.ExtentManager;
import Planit.TestComponents.BaseTest;

public class PurchaseOrderTest extends BaseTest {

	/*
	Test case 3:
	Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
	Go to the cart page
	Verify the subtotal for each product is correct
	Verify the price for each product
	Verify that total = sum(sub totals)	
	*/
	
	@Test(dataProvider = "getProductsData")
	public void verifyProductsInCart(JSONArray jsonData) throws InterruptedException {
		Map<String, ArrayList<String>> multiValueMap;
		double expectedTotal = 0;

		HomePage homePage = new HomePage(driver);
		ShopPage shopPage = new ShopPage(driver);
		CartPage cartPage = new CartPage(driver);

		homePage.goToShopPage();
		multiValueMap = shopPage.buyProducts(jsonData);
		homePage.goToCartPage();
		//cartPage.verifyProductPriceandSubtotal(multiValueMap);
		for (Map.Entry<String, ArrayList<String>> entry : multiValueMap.entrySet()) {
			String productName = entry.getKey();
			ArrayList<String> value = entry.getValue();
			String[] values = cartPage.verifyProductInfoInTheCart(productName);
			Assert.assertEquals(values[0], value.get(1));
			ExtentManager.getExtentTest().pass("Actual price value for "+productName+" is "+values[0]+" and expected value is "+value.get(1));
			double expectedSubTotal =cartPage.getexpectedSubTotalValue(value.get(1),value.get(0));
			expectedTotal = expectedTotal + expectedSubTotal;
			double actualSubTotal=Double.parseDouble(values[1].replace("$", ""));
			Assert.assertEquals(expectedSubTotal,actualSubTotal );
			ExtentManager.getExtentTest().pass("Actual subTotal value for "+productName+" is "+actualSubTotal+" and expected value is "+expectedSubTotal);
		}
		cartPage.verifyTotal(expectedTotal);
		}

	//Method for getting the test data 
	@DataProvider
	public Object[][] getProductsData() throws IOException, ParseException {
		JSONArray jsonData = getJsonData();
		return new Object[][] { { jsonData } };
	}

}
