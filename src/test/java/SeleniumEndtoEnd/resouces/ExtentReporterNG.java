package SeleniumEndtoEnd.resouces;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//index.html");
		reporter.config().setReportName("Automation Test Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("QA Member", "Akshat Jain");
		
		return extent;
	}
	
}
