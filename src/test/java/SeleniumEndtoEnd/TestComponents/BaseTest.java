package SeleniumEndtoEnd.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumEndtoEnd.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumEndtoEnd\\resouces\\GlobalData.properties");
		prop.load(fis);
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless"))
				options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.contains("firefox")) {
			//fire fox
			
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if(browser.contains("headless"))
				options.addArguments("headless");
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.contains("edge")) {
			
			//edge
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			if(browser.contains("headless"))
			options.addArguments("headless");
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {
			
			//read json to string
			String jsonData = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
			//convert json string to hashmap --> Jackson DataBind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){
			});
			
			return data;
	 	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@BeforeTest (alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
        landingPage = new LandingPage(driver);
		landingPage.goTo("https://rahulshettyacademy.com/client/");
		return landingPage;
	}
	
	@AfterTest  (alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
