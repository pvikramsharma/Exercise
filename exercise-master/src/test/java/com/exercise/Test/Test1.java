package com.exercise.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.exercise.PageObject.AddProductCategoryPO;
import com.exercise.PageObject.LoginPO;
import com.exercise.PageObject.MyAccountPO;
import com.exercise.PageObject.ShoppinCartSummaryPO;
import com.exercise.testBase.Config;
import com.exercise.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentTest;

public class Test1 extends TestBase {
public static String orderID;



	@Test
	public void AddToShoppingCart() throws InterruptedException, IOException {
		
		AddProductCategoryPO productCategory = new AddProductCategoryPO(driver,test);
		ShoppinCartSummaryPO shopCartSummary = new ShoppinCartSummaryPO(driver);
		MyAccountPO myAccount=new MyAccountPO(driver);
		Config config = new Config(OR);
		driver.get(config.getWebsite());
		driver.manage().window().maximize();
		productCategory.mouseOver("Faded");
		productCategory.quickViewFirstProduct();
		productCategory.switchIframe("fancybox-iframe");
		productCategory.selectSizeDropdown("M");
		productCategory.clickOnAddToCart();
		productCategory.continueShoppingClick();
		productCategory.mouseOver("Blouse");
		productCategory.quickViewSecondProduct();
		productCategory.switchIframe("fancybox-iframe");
		productCategory.clickOnAddToCart();
	Thread.sleep(2000);
		shopCartSummary.frameProceedCheckoutButton();
		AssertJUnit.assertEquals(shopCartSummary.getColorAndSize("Faded").get("Color"), "Orange");
		AssertJUnit.assertEquals(shopCartSummary.getColorAndSize("Faded").get("Size"), "M");
		AssertJUnit.assertEquals(shopCartSummary.getColorAndSize("Blouse").get("Color"), "Black");
		AssertJUnit.assertEquals(shopCartSummary.getColorAndSize("Blouse").get("Size"), "S");
		Double shipping = shopCartSummary.getShippingAmount();
		Double totalPrice = shopCartSummary.totalPrice();
		Double totalFromCart= shopCartSummary.getSumOfProductsInCart();
		AssertJUnit.assertEquals(totalPrice, totalFromCart + shipping);
		shopCartSummary.clickOnProceedTocheckOut();
		LoginPO loginPage = new LoginPO(driver);
		loginPage.loginToApplication(config.getUserName(), config.getPassword());
		shopCartSummary.alternateClickOnProceedTocheckOut();
		shopCartSummary.agreeToTerms();
		shopCartSummary.alternateClickOnProceedTocheckOut();
		shopCartSummary.payByWire();
		shopCartSummary.confirmOrder();
		orderID = shopCartSummary.getOrderId();
		

		Path path = Paths.get("./output.txt");
		 
		
		try (BufferedWriter writer = Files.newBufferedWriter(path))
		{
		    writer.write(orderID);
		    writer.close();
		}
		myAccount.logOut();
		driver.close();

	}

	public static void main(String... args) {
		/*
		 * Double f = 43.510002; DecimalFormat twoDForm = new DecimalFormat("#.##");
		 * System.out.println(twoDForm.format(f));
		 */
		String a= "Your order on My Store is complete.\n" + 
				"Please send us a bank wire with\n" + 
				"- Amount $47.33\n" + 
				"- Name of account owner Pradeep Macharla\n" + 
				"- Include these details xyz\n" + 
				"- Bank name RTP\n" + 
				"- Do not forget to insert your order reference PIPCIZXEQ in the subject of your bank wire.\n" + 
				"An email has been sent with this information.\n" + 
				"Your order will be sent as soon as we receive payment.\n" + 
				"If you have questions, comments or concerns, please contact our expert customer support team. .";
		String[] a1= a.split("-");
		String result =  StringUtils.substringBetween(a1[5], "reference ", " in");
		System.out.println(result);

	}
}
