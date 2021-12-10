package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage  {
	
	public WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}

	private By sharePrice=By.className("share-info");
	
	private By sharesvalue=By.xpath("//span/a/span[2]");
	
	public WebElement getSharePrice()
	{
		return driver.findElement(sharePrice);
		
	}
	public WebElement getShareValues()
	{
		return driver.findElement(sharesvalue);
		
	}
	
}
