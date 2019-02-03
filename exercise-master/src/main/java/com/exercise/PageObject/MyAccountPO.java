package com.exercise.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.exercise.helper.Logger.LoggerHelper;

public class MyAccountPO {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(MyAccountPO.class);
	
	
	@FindBy(xpath="//*[contains(text(),'Welcome to your account. Here you can manage all of your personal information and orders.')]")
	WebElement successRegistrationMsg;
	
	@FindBy(xpath="//*[contains(text(),'Order history and details')]")
	WebElement OrderHistoryAndDetails;
	
	@FindBy(xpath="//*[contains(text(),'My personal information')]")
	WebElement MyPersonalInformation;
	
	@FindBy(xpath="//a[@title='Log me out']")
	WebElement LogOut;
	
	public void logOut()
	{
		log.info("Signing out of the page");
		LogOut.click();
	}
	
	public void clickOnOrderHistory()
	{
		log.info("Clicked on Order History");
		OrderHistoryAndDetails.click();
	}
	
	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	

}
