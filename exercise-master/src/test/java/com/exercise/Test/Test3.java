package com.exercise.Test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.annotations.Test;

import com.exercise.PageObject.LoginPO;
import com.exercise.PageObject.MyAccountPO;
import com.exercise.PageObject.OrderHistoryPO;
import com.exercise.testBase.Config;
import com.exercise.testBase.TestBase;

public class Test3 extends TestBase {
public String orderID;

public String colorFromUI;
	@Test (description = "Test to check the color of the already ordered product and making it fail")
	public void testColorFailure() throws InterruptedException, IOException {
		
		try {
			OrderHistoryPO orderHistory = new OrderHistoryPO(driver);
			MyAccountPO myAccount=new MyAccountPO(driver);
			Config config = new Config(OR);
			driver.get(config.getWebsite());
			driver.manage().window().maximize();
			LoginPO loginPage = new LoginPO(driver);
			loginPage.clickOnSignIn();
			loginPage.loginToApplication(config.getUserName(), config.getPassword());
			myAccount.clickOnOrderHistory();
			orderHistory.searchByOrderId(orderHistory.getOrderId());
			colorFromUI=orderHistory.getTextBetweenTwoStrings("Color",",");
			assertEquals(colorFromUI, "Black");
			
			myAccount.logOut();
			driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void main(String... args) throws IOException {
		/*
		 * Double f = 43.510002; DecimalFormat twoDForm = new DecimalFormat("#.##");
		 * System.out.println(twoDForm.format(f));
		 */
		/*
		 * String a= "Your order on My Store is complete.\n" +
		 * "Please send us a bank wire with\n" + "- Amount $47.33\n" +
		 * "- Name of account owner Pradeep Macharla\n" +
		 * "- Include these details xyz\n" + "- Bank name RTP\n" +
		 * "- Do not forget to insert your order reference PIPCIZXEQ in the subject of your bank wire.\n"
		 * + "An email has been sent with this information.\n" +
		 * "Your order will be sent as soon as we receive payment.\n" +
		 * "If you have questions, comments or concerns, please contact our expert customer support team. ."
		 * ; String[] a1= a.split("-"); String result =
		 * StringUtils.substringBetween(a1[5], "reference ", " in");
		 */
		/*
		 * String fileName = "./output.txt";
		 * 
		 * //read file into stream, try-with-resources BufferedReader br = new
		 * BufferedReader(new FileReader(fileName )); String line = br.readLine();
		 * System.out.println(line);
		 */
	}


	
}
