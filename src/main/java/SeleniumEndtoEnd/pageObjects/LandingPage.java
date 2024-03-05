package SeleniumEndtoEnd.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumEndtoEnd.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	//parameterized constructor
	public LandingPage(WebDriver driver){
		//initializing
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//page factory
	@FindBy(id="userEmail")
	WebElement emailId;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;

	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	
	public String getError() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public ProductCatalog loginApplication(String eid, String pwd) {
		emailId.clear();
		password.clear();
		emailId.sendKeys(eid);
		password.sendKeys(pwd);
		loginBtn.click();
		return new ProductCatalog(driver);
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
}
