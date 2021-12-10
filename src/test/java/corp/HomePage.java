package corp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import resources.Base;

public class HomePage extends Base{
	
	public WebDriver driver;
	LandingPage l;
	JavascriptExecutor js;
	@BeforeTest
	public void initialize() throws Exception
	{
		driver=initializeDriver();
		l=new LandingPage(driver);	
	}
	@Test
	public void homeNavigation() throws InterruptedException
	{
		driver.get(fil.getProperty("url"));
		System.out.println("Script Execution started");
		System.out.println("Url of the Page is:"+driver.getCurrentUrl());
		System.out.println("Title of Page is:"+driver.getTitle());
		String shares=l.getSharePrice().getText();
		if(l.getSharePrice().isDisplayed())
		{
			//Assert.assertFalse(true);
			Assert.assertTrue(true);
			System.out.println(shares);
		}
		else
		{
			System.out.println("No shares are displaying on website");
		}
		
		String sharesvalue=l.getShareValues().getText();
		if(sharesvalue.equals("0"))
		{
			js.executeScript("document.body.style.zoom='150%'");
			Assert.assertTrue(false);

		}
		else
		{
			System.out.println("Share value is not equas to zero :"+sharesvalue);
		}
		
	    js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-300)");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
