package SeleniumEndtoEnd.Tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumEndtoEnd.TestComponents.BaseTest;
import SeleniumEndtoEnd.TestComponents.Retry;
import SeleniumEndtoEnd.pageObjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups= {"ErrorHandlingTests"})
	public void LoginErrorValidation() throws IOException {
		
		String emailId = "akshat@gmail.com";
		String password = "momDAD2@";
	
		landingPage.loginApplication(emailId, password);
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getError());
	}
	
	@Test
    public void ProductErrorValidation() throws IOException {
		
		String emailId = "akriti@gmail.com";
		String password = "momDAD2#";
		String item_needed = "Zara Coat 3";
		
		ProductCatalog pc = landingPage.loginApplication(emailId, password);
		
		pc.addItemToCart(item_needed);
		pc.goToCart();
		Assert.assertTrue(pc.checkIfItemIsInCart(item_needed));
		
	}
}
