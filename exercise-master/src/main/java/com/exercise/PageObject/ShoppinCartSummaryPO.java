package com.exercise.PageObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.exercise.helper.Logger.LoggerHelper;

public class ShoppinCartSummaryPO {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ShoppinCartSummaryPO.class);


	@FindBy(xpath = "//*[@id='columns']/div[1]/span[2]")
	WebElement yourShoppingCart;

	@FindBy(xpath = "//*[@id='columns']/div[1]/span[2]")
	List<WebElement> quantity_delete;

	@FindBy(xpath = "//*[contains(text(),'Your shopping cart is empty')]")
	WebElement emptyShoppingCartMsg;

	@FindBy(xpath = "//*[contains(text(),'Your shopping cart is empty')]")
	WebElement continueSHoppingButton;

	@FindBy(xpath = "//td[@id='total_product']")
	WebElement totalAmount;

	@FindBy(xpath = "//td[@id='total_shipping']")
	WebElement shippingAmount;

	@FindBy(xpath = "//td[@id='total_price_container']")
	WebElement totalPrice;

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	public WebElement frameProceedCheckoutButton;

	@FindBy(xpath = "//p//a[@title='Proceed to checkout']")
	public WebElement ProceedCheckoutButton;

	@FindBy(xpath = "//button//span[contains(text(),'Proceed to checkout')]")
	public WebElement alternateProceedCheckoutButton;

	@FindBy(xpath = "//*[@id='cgv']")
	public WebElement agreeTOC;

	@FindBy(xpath = "//*[@id='HOOK_PAYMENT']/div[1]/div/p/a")
	public WebElement payByWire;

	@FindBy(xpath = "//*[@id='cart_navigation']/button")
	public WebElement confirmOrder;

	@FindBy(xpath = "//*[@id='center_column']/div")
	public WebElement confMessage;

	public String getOrderId() {
		log.info("Get the Order Id");
		String a[] = confMessage.getText().split("-");
		String orderId = StringUtils.substringBetween(a[5], "reference ", " in");
		log.info("The orderId for this Order is " + orderId);
		return orderId;
	}

	public void confirmOrder() {
		log.info("Confirm the order ");
		confirmOrder.click();
	}

	public void payByWire() {
		log.info("Click on Pay by Wire ");
		payByWire.click();
	}

	public void agreeToTerms() {
		log.info("Agree to Terms and Condition ");
		agreeTOC.click();
	}

	public void clickOnProceedTocheckOut() {
		log.info("clicking on : " + ProceedCheckoutButton.getText());
		ProceedCheckoutButton.click();
	}

	public void frameProceedCheckoutButton() {
		log.info("clicking on : " + frameProceedCheckoutButton.getText());
		frameProceedCheckoutButton.click();
	}

	public void alternateClickOnProceedTocheckOut() {
		log.info("clicking on : " + alternateProceedCheckoutButton.getText());
		alternateProceedCheckoutButton.click();
	}

	public Double getShippingAmount() {

		return formatDoubleForTwoDecimals(Double.valueOf(getRidOfDollarSymbol(shippingAmount.getText())));
	}

	public Double totalPrice() {

		return formatDoubleForTwoDecimals(Double.valueOf(getRidOfDollarSymbol(totalPrice.getText())));
	}

	public Double getTotalAmount() {
		return formatDoubleForTwoDecimals(Double.valueOf(getRidOfDollarSymbol(totalAmount.getText())));
	}

	public ShoppinCartSummaryPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		waitHelper = new WaitHelper(driver);
//		waitHelper.waitForElement(driver, yourShoppingCart,new Config(TestBase.OR).getExplicitWait());
	}

	public Double getSumOfProductsInCart() {
		Double total = 0.00;
		List<WebElement> el1 = driver.findElements(
				By.xpath("//*[@id='order-detail-content']//table/tbody//../tr/td[@class='cart_total']/span"));
		for (WebElement w1 : el1) {
			total = total + Double.valueOf(getRidOfDollarSymbol(w1.getText()));
		}

		return formatDoubleForTwoDecimals(total);
	}

	public String getRidOfDollarSymbol(String amountWithDollar) {
		return amountWithDollar.substring(1, amountWithDollar.length());
	}

	public Double formatDoubleForTwoDecimals(Double total) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		System.out.println("This is from the Decimal Convertor method " + twoDForm.format(total));
		return Double.valueOf(twoDForm.format(total));
	}

	public Map<String, String> getColorAndSize(String productname) {
		List<WebElement> el1;
		el1 = driver.findElements(By.xpath("//*[@id='order-detail-content']//table/tbody//../tr//*[contains(text(),'"
				+ productname + "')]//../..//../small"));

		HashMap<String, String> mapSizeColor = null;
		for (WebElement w1 : el1) {

			if (w1.getText().contains("Color")) {
				String[] color = w1.getText().split(","); // split the string to creat key-value pairs
				mapSizeColor = new HashMap<String, String>();
				for (String pair : color) // iterate over the pairs
				{
					String[] entry = pair.split(":"); // split the pairs to get key and value
					mapSizeColor.put(entry[0].trim(), entry[1].trim()); // add them to the hashmap and trim whitespaces
				}
			}
		}
		return mapSizeColor;
	}

	

	

	public static void main(String... args) {
		/*
		 * Double f = 43.510002; DecimalFormat twoDForm = new DecimalFormat("#.##");
		 * System.out.println(twoDForm.format(f));
		 */

	}
}
