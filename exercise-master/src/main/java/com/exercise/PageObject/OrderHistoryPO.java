package com.exercise.PageObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.exercise.helper.Logger.LoggerHelper;
import com.exercise.testBase.TestBase;

public class OrderHistoryPO {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(OrderHistoryPO.class);


	@FindBy(xpath = "//i[@class='icon-home']")
	WebElement clickOnHome;


	@FindBy(xpath = "//*[@id='sendOrderMessage']/p[2]/select")
	WebElement productDropDown;
	
	@FindBy(xpath = "//*[@id='sendOrderMessage']/p[3]/textarea")
	WebElement typeCommentTetArea;
	
	@FindBy(xpath="//*[@id='sendOrderMessage']/div/button")
	WebElement sendMessageButton;
	
	@FindBy(xpath="//table//td[text()='Testing']")
	WebElement msgInTable;
	
	@FindBy(xpath="//p[normalize-space(text())='Message successfully sent']")
	WebElement messageSavedText;
	
	@FindBy(xpath="//table/thead//th[text()='Message']/ancestor::table//tr[contains(@class,'first_item')]/td[2]")
	WebElement getSavedMessage;
	
	@FindBy(xpath="(//table/thead//th[text()='Product']/ancestor::table//tr[@class='item']/td[@class='bold']/label)[1]")
	WebElement firstProductInfo;
	
	public String getSavedMessage() {
		TestBase.waitForElementToBeVisible(driver, 4, messageSavedText);
		log.info("The saved comment is " + StringUtils.deleteWhitespace(getSavedMessage.getText()) );
		return (getSavedMessage.getText());
	}
	
	public String getFirstProductInfo() {
		TestBase.waitForElementToBeVisible(driver, 4, firstProductInfo );
		log.info("The product info is  " + firstProductInfo.getText() );
		return firstProductInfo.getText();
	
	}
	
	public void confirmMsgInTable(String msg)
	{
		log.info("Confirm the message is saved in the table" );
		TestBase.waitForElementToBeVisible(driver, 4, messageSavedText);
		driver.findElement(By.xpath("//table//td[text()='"+msg+"']"));
	}
	
	public void sendMessage()
	{
		log.info("click on the send message");
		sendMessageButton.click();
	}
	
	public String getTextBetweenTwoStrings(String a, String b) {
		log.info("Get the Color");
		String getText = StringUtils.substringBetween(getFirstProductInfo(), a, b);
		log.info("The orderId for this Order is " + getText);
		return getText;
	}
	
	public void typeMessage(String msg)
	{
		log.info("Enter the comment");
		typeCommentTetArea.sendKeys(msg);
	}

	public void searchByOrderId(String orderId) {
		log.info("Click on the Order Id :" + orderId);
		driver.findElement(By.xpath("//td//a[contains(text(),'"+orderId+"')]")).click();
	}

	
	public void selectProduct() {
		log.info("Select Product from dropdown");
		TestBase.waitForElement(driver, 4 , productDropDown);
		Select productDrop=new Select(productDropDown);
		productDrop.selectByIndex(1);
	
	}
	public OrderHistoryPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
		
		// waitHelper = new WaitHelper(driver);
		// waitHelper.waitForElement(driver, firstProduct, new
		// Config(TestBase.OR).getExplicitWait());
	}

	public String getOrderId() throws IOException
	{
		String fileName = "./output.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        br.close();
        return line;
	}



}
