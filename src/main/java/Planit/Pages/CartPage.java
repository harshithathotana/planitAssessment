package Planit.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Planit.AbstractComponents.AbstractMethods;
import Planit.Resources.ExtentManager;

public class CartPage extends AbstractMethods {
	WebDriver driver;

	//Web elements of Cart Page
	By productNamesBy = By.cssSelector("tbody tr td");
//	By productPrice = By.cssSelector("td:nth-child(2)");
//	By productQuantity = By.cssSelector("td:nth-child(3) input");
//	By productSubTotal = By.cssSelector("td:nth-child(4)");
	By tableBody=By.xpath("//table/tbody");
	By total = By.cssSelector(".total");
	By rows = By.tagName("tr");
	By columns=By.tagName("td");

	String priceValue="//tbody/tr[%rowNumber%]/td[2]";
	String subTotalValue="//tbody/tr[%rowNumber%]/td[4]";

	//Constructor of Cart Page
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//Method to verify the product information(i.e., price of each product and its sub total value in the cart)
	public String[] verifyProductInfoInTheCart(String productName) {
		String[] values = new String[2];
			WebElement tbleBody = driver.findElement(tableBody);
			List<WebElement> tableRows = tbleBody.findElements(rows);
			//int rowCount = tableRows.size();
			int rowNumber = 0;
			for (WebElement rowElement : tableRows) {
				rowNumber = rowNumber + 1;
				List<WebElement> columnValues = rowElement.findElements(columns);
				//int colCount = columnValues.size();
				for (WebElement col : columnValues) {
					if (col.getText().equalsIgnoreCase(productName)) {
						values[0] = driver.findElement(By.xpath(priceValue.replace("%rowNumber%",String.valueOf(rowNumber)))).getText();
						values[1] = driver.findElement(By.xpath(subTotalValue.replace("%rowNumber%",String.valueOf(rowNumber)))).getText();
						//values[0] = driver.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[2]")).getText();
						//values[1] = driver.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[4]")).getText();
					}
				}
			}

		return values;
	}

	//Method to get the cart total value
	public double getCartTotal() {
		String value= getText(total,"Total Cart");
		return Double.parseDouble(value.split("\\s+")[1]);
	}
	
	//Method to get the expected sub-total value
	public double getexpectedSubTotalValue(String productPrice,String priceCount) {
		double value= Double.parseDouble(productPrice.replace("$", ""))
				* Integer.parseInt(priceCount);
		return value;
	}

	//Method to verify the actual total value in the cart page
	public void verifyTotal(double expectedTotal) {
		double total=getCartTotal();
		Assert.assertEquals(expectedTotal,total);
		ExtentManager.getExtentTest().pass("Actual Total is "+total +" and expected total is "+expectedTotal);
	
	}
}
