package SeleniumEndtoEnd.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumEndtoEnd.pageObjects.CheckOutPage;
import SeleniumEndtoEnd.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
			
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection")
	List<WebElement> cart_items;
	
	@FindBy(css=".btn-custom[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	@FindBy(css="li:nth-child(5) button:nth-child(1)")
	WebElement signOutBtn;
	
	
	public OrderPage goToOrders() {
		orders.click();
		return new OrderPage(driver);
	}
	
	public void signOut() {
		signOutBtn.click();
	}
	
	public void waitForWebElementToClickable(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToAppear(By productsBy) {
		
		//explicit wait
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(productsBy));
	}
	
    public void waitForWebElementToAppear(WebElement productsBy) {
		
		//explicit wait
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(productsBy));
	}
	
    public void waitForElementToDissappear(By productsBy) {
		
		//explicit wait
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		w.until(ExpectedConditions.invisibilityOfElementLocated(productsBy));
	}
    
	
	public boolean checkIfItemIsInCart(String item) {
		
		return cart_items.stream().anyMatch(itr->itr.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(item));
	}
	
	public CheckOutPage proceedToCheckOut() {
		checkOutBtn.click();
		return new CheckOutPage(driver);
	}
}
