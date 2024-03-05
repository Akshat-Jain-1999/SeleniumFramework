package SeleniumEndtoEnd.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int limit =2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<limit) {
			count++;
			return true;
		}
		
		return false;
	}	
}
