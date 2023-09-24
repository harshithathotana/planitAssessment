package Planit.Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Planit.AbstractComponents.AbstractMethods;
import Planit.Resources.ExtentManager;

public class ShopPage extends AbstractMethods {
	WebDriver driver;

	/*
	By product1Value = By.xpath("//*[text()='" + product1 + "']/following-sibling::p/span");
	By product1BuyButton = By.xpath("//*[text()='" + product1 + "']/following-sibling::p/a");
	*/
	
	//Web elements of Shop Page
	By productsBy = By.cssSelector(".product");
	By buyButton=By.linkText("Buy");
	//By productHeading=By.cssSelector("h4");
	By productHeading=By.className("product-title");
	By productPrice=By.cssSelector(".product-price");
	
	//Constructor of Shop Page
	public ShopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	//Method to be used for getting the name of the product from the products list
	public WebElement getProductByName(String productName)
	{
		List<WebElement> products=getElements(productsBy);
		WebElement prod =	products.stream().filter(product->
		product.findElement(productHeading).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	//Method to be used for adding the product to the cart based on productName and product count
	public void addProductToCart(String productName,int productCount) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		for(int i=1;i<=productCount;i++)
			prod.findElement(buyButton).click();
		ExtentManager.getExtentTest().info("Total "+productCount+" "+ productName+"(s) got added to cart");
	}
	
	//Method to be used for getting the product price based on the productName
	public String getProductPrice(String productName) {
		String price=null;
		WebElement prod = getProductByName(productName);
		price=prod.findElement(productPrice).getText();
		ExtentManager.getExtentTest().info(productName+" price is:"+price);
		
		return price;
	}
	
	//Method to be used for buying the product 
	public void buyProduct(String productName, String productCount) throws InterruptedException {
		addProductToCart(productName,Integer.parseInt(productCount));
	}

	//Method to be used for buying multiple products by converting the json data(input) into a multivalue Hashmap
	public Map<String, ArrayList<String>> buyProducts(JSONArray jsonData) throws InterruptedException {
		Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();

		for(int i=0;i<jsonData.size();i++) 
		{
		JSONObject dataSet = (JSONObject) jsonData.get(i);
		//JSONObject data = (JSONObject) dataSet.get("products");
		String productName = (String) dataSet.get("productName");
		String productCount = (String) dataSet.get("productCount");
		multiValueMap.put(productName, new ArrayList<String>());
		multiValueMap.get(productName).add(productCount);
		buyProduct(productName,productCount);
		multiValueMap.get(productName).add(getProductPrice(productName));
		}
		return multiValueMap;
	}
	
}
