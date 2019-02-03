package com.exercise.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.exercise.helper.Logger.LoggerHelper;
import com.exercise.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class AddProductCategoryPO {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(AddProductCategoryPO.class);


	public String productname;

	@FindBy(xpath = "//i[@class='icon-home']")
	WebElement clickOnHome;

	@FindBy(xpath = "//*[@id='layered_block_left']/p")
	WebElement catalogTextObj;

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement productAddedSucessfully;

	@FindBy(xpath = "//*[@id='add_to_cart']/button/span")
	WebElement addToCart;

	@FindBy(xpath = "//div/span[@title='Continue shopping']")
	WebElement continueShopping;

	@FindBy(xpath = "//*[@id='center_column']/ul/li")
	List<WebElement> totalProducts;

	@FindBy(xpath = "//*[@id='center_column']/ul/li/div/div[2]/div/span[1]")
	List<WebElement> allpriceElements;

	@FindBy(xpath = "//*[@id='homefeatured']/li[1]/div/div[1]/div/a[2]/span")
	public WebElement firstProduct;

	@FindBy(xpath = "//*[@id='homefeatured']/li[2]/div/div[1]/div/a[2]/span")
	public WebElement secondProduct;

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	public WebElement proceedCheckoutButton;

	@FindBy(xpath = "//select[@id='group_1']")
	WebElement sizeDropdow;

	public void mouseOver(String productname) {
		log.info("doing mouse over on :" + productname);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a/img[contains(@title,'" + productname + "')]"))).build()
				.perform();
	}

	public AddProductCategoryPO(WebDriver driver, ExtentTest Test) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
		// waitHelper = new WaitHelper(driver);
		// waitHelper.waitForElement(driver, firstProduct, new
		// Config(TestBase.OR).getExplicitWait());
	}

	public void clickOnAddToCart() {
		log.info("clickin on add to cart");
		addToCart.click();
	}

	public void continueShoppingClick() {
		log.info("Continue Shopping");
		TestBase.waitForElement(driver, 2 , continueShopping);
		continueShopping.click();
	}

	public void quickViewFirstProduct() {
		log.info("Click on the quick view of first test product");
		TestBase.waitForElement(driver, 2 , firstProduct);
		firstProduct.click();
	}

	public void quickViewSecondProduct() {
		log.info("Click on the quick view of the second test product");
		secondProduct.click();
	}

	public void clickOnProceedTocheckOut() {
		log.info("clickin on : " + proceedCheckoutButton.getText());
		TestBase.waitForElement(driver, 2 , proceedCheckoutButton);
		proceedCheckoutButton.click();
	}

	



	public void selectSizeDropdown(String size) {

		Select sel1 = new Select(sizeDropdow);
		
		sel1.selectByVisibleText(size);
	}



	public int getTotalProducts() {
		return totalProducts.size();
	}

	public void switchIframe(String iframName) {
		WebElement iframeSwitch = driver.findElement(By.className(iframName));
		driver.switchTo().frame(iframeSwitch);

	}

}
