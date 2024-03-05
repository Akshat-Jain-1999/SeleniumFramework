package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import SeleniumEndtoEnd.TestComponents.BaseTest;
import SeleniumEndtoEnd.pageObjects.CheckOutPage;
import SeleniumEndtoEnd.pageObjects.ConfirmationPage;
import SeleniumEndtoEnd.pageObjects.LandingPage;
import SeleniumEndtoEnd.pageObjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog pc;
	public CheckOutPage cop;
	public ConfirmationPage cp;
	
	@Given("I landed on E Commerce Page")
	public void I_landed_on_E_Commerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_passsword(String username,String password){
		pc = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) {
		pc.addItemToCart(productName);
		pc.goToCart();
		Assert.assertTrue(pc.checkIfItemIsInCart(productName));
	}
	
	@And("^Check Out (.+) and Submit the Order$")
	public void checkout_and_submit_order(String productName) {
		cop = pc.proceedToCheckOut();
		cp = cop.fillDetailsAndPlaceOrder("india");
	}
	
	@Then("I verify the message {string} on Confirmation Page.")
	public void I_verify_message_cp(String message) {
		String confirmationMsg = cp.confirmOrder();
		Assert.assertEquals(confirmationMsg,message);
		pc.signOut();
		driver.close();
	}
	
	@Then("Message {string} is verified")
    public void message_is_verified(String expectedErrorMessage) {
		Assert.assertEquals(expectedErrorMessage, landingPage.getError());
		driver.close();
    }	
}
