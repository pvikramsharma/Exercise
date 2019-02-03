package com.exercise.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.exercise.helper.Logger.LoggerHelper;


public class LoginPO{
	
	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPO.class);
	;
	
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signin;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement emailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath="//*[@id='SubmitLogin']")
	WebElement submitLogin;
	


	public LoginPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		/*
		 * waitHelper = new W(driver); waitHelper.waitForElement(driver, signin,new
		 * Config(TestBase.OR).getExplicitWait());
		 */
	}
	
	public void clickOnSignInLink(){
		log.info("clicked on sign in link...");
		signin.click();
	}
	
	public void enterEmailAddress(String emailAddress){
		log.info("entering email address...."+emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password){
		log.info("entering password...."+password);
		this.password.sendKeys(password);
	}
	
	public void clickOnSubmitButton(){
		log.info("clicking on submit button...");
	
		submitLogin.click();
		return ;
	}
	
public void clickOnSignIn()
{
	
	log.info("Click on Sign In Link");
	clickOnSignInLink();
}
	public void loginToApplication(String emailAddress, String password){
		//clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}

}
