package Planit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Planit.AbstractComponents.AbstractMethods;

public class HomePage extends AbstractMethods {
	WebDriver driver;
//	@FindBy(linkText="Contact")
//	 WebElement contactLink;
//	
//	@FindBy(css = "a[href='#/cart']")
//	WebElement cartLink;
//	
//	@FindBy(linkText = "Shop")
//	WebElement shopLink;
	
	//Web elements of Home Page
	By contactLink=By.linkText("Contact");
	By cartLink=By.cssSelector("a[href='#/cart']");
	By shopLink=By.linkText("Shop");
	
	//Constructor of Home Page
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//PageFactory.initElements(driver, this);
	}
	
	//Method to be used for navigating to contact page
	public void goToContactPage() {
		click(contactLink,"Contact page link");
	}
	
	//Method to be used for navigating to Cart page
	public void goToCartPage() {
		click(cartLink,"Cart page link");
	}
	
	//Method to be used for navigating to Shop page
	public void goToShopPage() {
		click(shopLink,"Shop page link");
	}
}
