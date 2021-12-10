package corp;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporter;

public class Listeners extends Base implements ITestListener{
	ExtentReports extent=ExtentReporter.getReportObject();
	ExtentTest test;
	String testMethodName;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {

		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WebDriver driver=null;
		
		String testMethodName=result.getMethod().getMethodName();
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
    	
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
    	
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName,driver),result.getMethod().getMethodName());//parameters- media path, title
			//getScreenshot(testMethodName,driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().log(Status.PASS,"Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		extentTest.get().fail(result.getThrowable());
		String testMethodName=result.getMethod().getMethodName();
		
	try {
		//The real class on which this method was declared
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName,driver),result.getMethod().getMethodName());//parameters- media path, title
			//getScreenshot(testMethodName,driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

	
}
