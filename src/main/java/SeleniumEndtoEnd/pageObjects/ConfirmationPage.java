package SeleniumEndtoEnd.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumEndtoEnd.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;
	
	//parameterized constructor
	public ConfirmationPage(WebDriver driver){
		//initializing
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement text;
	
	
	public String confirmOrder() {
		return text.getText();
	}
}
