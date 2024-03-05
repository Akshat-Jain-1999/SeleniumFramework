package SeleniumEndtoEnd.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumEndtoEnd.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	
	//parameterized constructor
	public OrderPage(WebDriver driver){
		//initializing
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="td:nth-child(3)")
	List<WebElement> orderedItems;
	
	
	
	public boolean verifyOrderDisplay(String item_needed) {
		
		return orderedItems.stream().anyMatch(itr->itr.getText().equalsIgnoreCase(item_needed));
		
	}
}
