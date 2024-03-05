package SeleniumEndtoEnd.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumEndtoEnd.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

	WebDriver driver;
	
	//parameterized constructor
	public ProductCatalog(WebDriver driver){
		//initializing
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
	By cartButton;
	
	By productsBy = By.cssSelector(".card-body");
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String product) {
		return getProductList().stream().filter(itr->itr.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(product)).findFirst().orElse(null);
	}
	
	public void addItemToCart(String item) {
		getProductByName(item).findElement(By.cssSelector("button:last-of-type")).click();
	}
	
	public void goToCart() {
		//waits
        //waitForElementToAppear(By.id("toast-container"));
		waitForElementToDissappear(By.cssSelector(".ng-animating"));
		waitForWebElementToClickable(cartBtn);
		cartBtn.click();
	}
}
