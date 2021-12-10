package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public WebDriver driver;//driver obj created only once ,this obj will control entire test case
	public Properties fil;
	//reusable utility
	public WebDriver initializeDriver() throws Exception
	{
		fil=new Properties();

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\browser.properties");

		fil.load(fis);

		//System.getProperty("user.dir"); //gives current dir path 

		String projectPath = System.getProperty("user.dir");
		String browserName=fil.getProperty("browser");   
		//String browserName=System.getProperty("browser");//using below code line to run from maven with parameters [maven treats all this values as system properties]
		//if(browserName=="chrome")whenever we are extracting the value from some property then we cannot give ==
		if(browserName.contains("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			String chrome_driver_path="\\src\\main\\java\\resources\\chromedriver.exe";

			System.setProperty("webdriver.chrome.driver",projectPath+chrome_driver_path);

			//to run the same with jenkins use mvn test -Dbrowser=chromeheadless
			if(browserName.contains("headless"))
			{
			ChromeOptions options=new ChromeOptions();
			//options.addArguments("--headless"); //knowledge of headless is given here
			options.setHeadless(true);
			driver=new ChromeDriver(options);
			}
			else
			{
				driver=new ChromeDriver();
				driver.manage().window().maximize();
			}
			

		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","F://browsers//gecko//geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("ie"))
		{
			System.setProperty("webdriver.edge.driver","F://browsers//edge//msedgedriver.exe");
			driver=new EdgeDriver();
		}

		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); no life for this obj, there is nothing inside this driver	
		return driver;
	}

	public String getScreenshot(String testcasename,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);//this file is in virtual location
		String destinationFile=System.getProperty("user.dir")+"\\screenshots\\"+testcasename+".png";
		FileHandler.copy(src,new File(destinationFile));//from copy file from virtual to local system we have filehandles and fileutils method
		return destinationFile;
	}

}
