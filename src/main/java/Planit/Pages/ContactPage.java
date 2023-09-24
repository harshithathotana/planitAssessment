package Planit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Planit.AbstractComponents.AbstractMethods;

public class ContactPage extends AbstractMethods {
	WebDriver driver;

	//Web elements of Contact Page
	By foreNameErrorMessage = By.id("forename-err");
	By emailErrorMessage = By.id("email-err");
	By messageErrorMessage = By.id("message-err");
	By foreNameInputbox = By.name("forename");
	By emailInputbox = By.name("email");
	By messageTextbox = By.name("message");
	By successFeedbackTextMessage = By.cssSelector(".alert.alert-success");
	By submitButton = By.linkText("Submit");

	//Constructor of Contact Page
	public ContactPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//Method to be used to get success message after submitting the contact page form
	public String getSuccessMessage() {
		waitForElementToBeVisible(successFeedbackTextMessage, 30);
		String value = getText(successFeedbackTextMessage, "Sucess feedback Text Message");
		return value;
	}

	//Method to be used for clicking the submit button
	public void clickSubmit() {
		click(submitButton, "Submit button");
	}
//Method to be used for verify if the error messages are appeaing for the mandatory fields
	public boolean verifyMandatoryFieldErrorMessages() {
			boolean check1 = verifyIfElementIsDisplayed(foreNameErrorMessage, "Forename field error message");
			boolean check2 = verifyIfElementIsDisplayed(emailErrorMessage, "Email field error message");
			boolean check3 = verifyIfElementIsDisplayed(messageErrorMessage, "Message Field error message");
			if (check1 && check2 && check3) {
				return true;
			} else {
				return false;
			}

	}
// Method to be used for verify the error message for the mandatory fields
	public boolean verifyMandatoryFieldErrorMessagesText(String forenameErrorText, String emailErrorText,
			String messageErrorText) {
			if ((getText(foreNameErrorMessage, "Forename field error message").equalsIgnoreCase(forenameErrorText))
					&& (getText(emailErrorMessage, "Email field error message").equalsIgnoreCase(emailErrorText))
					&& (getText(messageErrorMessage, "Message field error message").equalsIgnoreCase(messageErrorText))) {
				return true;
			}else {
				return false;
			}

	}
//	public void verifyErrorMessageContent() {
//		if(getText(foreNameErrorMessage).equalsIgnoreCase(forenameErrorText)
//	}

	//Method to be used for populating mandatory fields
	public void populateMandatoryFields(String forename, String email, String message) throws InterruptedException {
		enterData(foreNameInputbox, forename);
		enterData(emailInputbox, email);
		enterData(messageTextbox, message);

	}

	//Method to be used for verifying if error messages for the mandatory fields are disappeared
	public boolean verifyIfErrorMessagesDisappeared(String forenameErrorText, String emailErrorText,
			String messageErrorText) throws InterruptedException {

		boolean check1 = waitForElementToDisappear(foreNameErrorMessage, 10, "Forename field error message");
		boolean check2 = waitForElementToDisappear(emailErrorMessage, 10, "Email field error message");
		boolean check3 = waitForElementToDisappear(messageErrorMessage, 10, "Email field error message");
		// Alternative ways to achieve the same
		// Option 2: By checking if the element size is greater than 0
//		boolean check1=verifyIfElementPresent(foreNameErrorMessage);
//		boolean check2=verifyIfElementPresent(emailErrorMessage);
//		boolean check3=verifyIfElementPresent(messageErrorMessage);
		// Option 3: By checking if the error text is present in the page DOM
//		verifyTextPresentOnThePageSource(forenameErrorText);
//		verifyTextPresentOnThePageSource(emailErrorText);
//		verifyTextPresentOnThePageSource(messageErrorText);

		// Option 4: Is using the verifyMandatoryFieldErrorMessagesText() method

		if (check1 && check2 && check3) {
			return true;
		} else {
			return false;
		}
	}
}
