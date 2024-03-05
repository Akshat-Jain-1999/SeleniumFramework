package SeleniumEndtoEnd.Tests;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumEndtoEnd.TestComponents.BaseTest;
import SeleniumEndtoEnd.TestComponents.Retry;
import SeleniumEndtoEnd.pageObjects.CheckOutPage;
import SeleniumEndtoEnd.pageObjects.ConfirmationPage;
import SeleniumEndtoEnd.pageObjects.OrderPage;
import SeleniumEndtoEnd.pageObjects.ProductCatalog;

public class PageObjectSolutionCode extends BaseTest{
	
	//String item_needed = "Zara Coat 3";
	
	@Test (dataProvider ="getData",groups = {"Purchase"})
	 public void OrderItem(HashMap<String,String> data) throws IOException, InterruptedException{
		String country ="india";
		ProductCatalog pc = landingPage.loginApplication(data.get("email"),data.get("password"));
		
		
		pc.addItemToCart(data.get("product"));
		
		pc.goToCart();
		Assert.assertTrue(pc.checkIfItemIsInCart(data.get("product")));
		
		CheckOutPage cop = pc.proceedToCheckOut();
		ConfirmationPage cp = cop.fillDetailsAndPlaceOrder(country);
		
		String confirmationMsg = cp.confirmOrder();
		Assert.assertEquals(confirmationMsg,"THANKYOU FOR THE ORDER.");
		pc.signOut();
	}
	
	//to verify item_needed is present in orders page after placing the order
	@Test (dependsOnMethods= {"OrderItem"})
	public void OrderHistoryTest() {
		String item_needed = "Zara Coat 3";
		ProductCatalog pc = landingPage.loginApplication("akshat@gmail.com", "momDAD2#");
		OrderPage op = pc.goToOrders();
		Assert.assertTrue(op.verifyOrderDisplay(item_needed));
	}
	

	//Extent Reports
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumEndtoEnd//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
