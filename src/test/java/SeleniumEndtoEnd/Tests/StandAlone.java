package SeleniumEndtoEnd.Tests;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver","C:/Users/aksjain/Documents/drivers/chromedriver.exe");
		String emailId = "akshat@gmail.com";
		String password = "momDAD2#";
		String item_needed = "ZARA coat 3";
		//List<String> items_needed = Arrays.asList("Coat","iphone");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//explicit wait
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys(emailId);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		WebElement item = products.stream().filter(itr->itr.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item_needed)).findFirst().orElse(null);
		item.findElement(By.cssSelector("button:last-of-type")).click();
		//assertTrue("Product Added to Cart",driver.findElement(By.id("toast-container")).getText());
		
		
		
		//ng-animating -->class for loading animation
		
		//wait until Product is added successfully
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		//wait until loading animation is gone
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		//Moving to Cart
		
		List<WebElement> cart_items = driver.findElements(By.cssSelector(".cartSection"));
		Boolean bool = cart_items.stream().anyMatch(itr->itr.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(item_needed));
		Assert.assertTrue(bool);
		//WebElement item_in_cart = cart_items.stream().filter(itr->itr.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(item_needed)).findFirst().orElse(null);
		//System.out.println(item_in_cart.findElement(By.cssSelector(".itemNumber")).getText());
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//we can do sendkeys using Actions also
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind");
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> places = driver.findElements(By.xpath("//button[@type='button']"));
		places.stream().filter(itr->itr.findElement(By.cssSelector(".ng-star-inserted span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	}
}
