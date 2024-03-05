package SeleniumEndtoEnd.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumEndtoEnd.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	
	//parameterized constructor
	public CheckOutPage(WebDriver driver){
		//initializing
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryInputBox;
	
	@FindBy(xpath="//button[@type='button']")
	List<WebElement> places;
	
	@FindBy(css=".actions a")
	WebElement placeOrder;
	
	@FindBy(css=".hero-primary")
	WebElement text;
	
	
	public ConfirmationPage fillDetailsAndPlaceOrder(String country) {
		countryInputBox.sendKeys(country);
		places.stream().filter(itr->itr.findElement(By.cssSelector(".ng-star-inserted span")).getText().equalsIgnoreCase(country)).findFirst().orElse(null).click();
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
}
