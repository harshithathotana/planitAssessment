package Planit.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Planit.Resources.ExtentManager;

public class AbstractMethods {

	WebDriver driver;

	//Constructor of Abstract Methods
	public AbstractMethods(WebDriver driver) {
		
		this.driver = driver;
	}
	
	//Generic method for clicking element
	public void click(By locator,String elementName) {
		driver.findElement(locator).click();
		ExtentManager.getExtentTest().info(elementName+" is clicked");
	}
	
	//Generic method for getting text from the element
	public String getText(By locator,String elementName) {
		String value=null;
		value=driver.findElement(locator).getText();
		ExtentManager.getExtentTest().info(elementName+" has value: "+value);
		return value;
	}
	
	//Generic method for verifying if the element is displayed
	public boolean verifyIfElementIsDisplayed(By locator, String elementName) {
		boolean check=false;
		check=driver.findElement(locator).isDisplayed();
		ExtentManager.getExtentTest().info(elementName+" is displayed");
		return check;
	}
	
	//Generic method for entering the value in an element
	public void enterData(By locator,String data) {
		driver.findElement(locator).sendKeys(data);
		ExtentManager.getExtentTest().info(data+" value is entered");
	}
	
	//Generic method for verifying if the text is present on the page source
	public void verifyTextPresentOnThePageSource(String data) {
		if(driver.getPageSource().contains(data))
		{
			ExtentManager.getExtentTest().info("'"+data+"'"+" text is present on the page");
		}
		else {
			ExtentManager.getExtentTest().info("'"+data+"'"+" text is not present on the page");
			
		}
	}
	
	
	//Generic method for verifying if the element is present
	public boolean verifyIfElementPresent(By locator) {
		if(driver.findElements(locator).size()>0)
		{
			ExtentManager.getExtentTest().info("Element is present");
			return true;
		}
		else {
			ExtentManager.getExtentTest().info("Element is not present");
			return false;
		}
	}
	
	//Generic method to wait for the element to appear
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	//Generic method to wait for the element to be visible
	public void waitForElementToBeVisible(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	//Generic method to wait for the element to disappear
	public boolean waitForElementToDisappear(By locator, int seconds,String element) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		boolean check=wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		if(check) {
			ExtentManager.getExtentTest().info(element+" element is not visible");
			return true;
		}else {
			ExtentManager.getExtentTest().info(element+" element is visible");
			return false;
		}
	}
	
	//Generic/Business method for getting elements from the list of elements
	public List<WebElement> getElements(By locator) { 
		List<WebElement> productNames = null;
		try {
			waitForElementToAppear(locator);
			productNames=driver.findElements(locator); 
			} catch (Exception e) { 
				e.printStackTrace(); 
	 } return productNames; 
	 }

}
