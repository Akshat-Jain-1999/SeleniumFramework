package SeleniumEndtoEnd.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumEndtoEnd.resouces.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	@Override  
	public void onTestStart(ITestResult result) {  
	// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub  
		extentTest.get().log(Status.PASS, "Test Passed successfully");
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {  
	// TODO Auto-generated method stub
	
		extentTest.get().fail(result.getThrowable());
		
	//Screenshot
	
	try {
		driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String path = null;
	try {
		path = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
	
	
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub  
	  
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	// TODO Auto-generated method stub  
	  
	}   
	
	@Override  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
	// TODO Auto-generated method stub  
		extent.flush();
	}  
	
}
