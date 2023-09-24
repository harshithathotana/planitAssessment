package Planit.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Planit.Pages.ContactPage;
import Planit.Pages.HomePage;
import Planit.TestComponents.BaseTest;

public class ContactPageTest extends BaseTest {
	
	/*
	 Test case 1:
	From the home page go to contact page
	Click submit button
	Verify error messages
	Populate mandatory fields
	Validate errors are gone	
	*/
	
	@Test(dataProvider="getContactData")
	public void errorValidationsforMandatoryFields(HashMap<String,String> contactData) throws InterruptedException {
		HomePage homePage=new HomePage(driver);
		ContactPage contactPage= new ContactPage(driver);
		
		homePage.goToContactPage();
		contactPage.clickSubmit();
		Assert.assertTrue(contactPage.verifyMandatoryFieldErrorMessages());
		Assert.assertTrue(contactPage.verifyMandatoryFieldErrorMessagesText(contactData.get("forenameErrorText"),contactData.get("emailErrorText"),contactData.get("messageErrorText")));
		contactPage.populateMandatoryFields(contactData.get("forename"),contactData.get("email"),contactData.get("message"));
		Assert.assertTrue(contactPage.verifyIfErrorMessagesDisappeared(contactData.get("forenameErrorText"),contactData.get("emailErrorText"),contactData.get("messageErrorText")));
		//Assert.assertFalse(contactPage.verifyMandatoryFieldErrorMessages());
	}
	
	
	/*
	Test case 2:
	1. From the home page go to contact page
	2. Populate mandatory fields
	3. Click submit button
	4. Validate successful submission message
	Note: Run this test 5 times to ensure 100% pass rate	
	*/
	@Test(dataProvider="getContactData",invocationCount = 5)
	public void verifySuccessfulSubmissionMessage(HashMap<String,String> contactData) throws InterruptedException {
		HomePage homePage=new HomePage(driver);
		ContactPage contactPage= new ContactPage(driver);
		
		homePage.goToContactPage();
		contactPage.populateMandatoryFields(contactData.get("forename"),contactData.get("email"),contactData.get("message"));
		contactPage.clickSubmit();
		Assert.assertEquals(contactPage.getSuccessMessage(),"Thanks " + contactData.get("forename") + ", we appreciate your feedback.");
	}
	
	//Method to get the test data sets from the input json file
	@DataProvider
	public Object[][] getContactData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Planit//TestData//ContactPageData.json");
		return new Object[][]  {{data.get(0)}};
	}
	
}
