package resources;

import java.time.Duration;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
		
	WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {


	prop = new Properties();
		
	String propPath = System.getProperty("user.dir")+"//src//main//java//resources//data.properties";
	FileInputStream fis = new FileInputStream(propPath);
	
	prop.load(fis);
	
	String browserName = prop.getProperty("browser");
	
	if (browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	else if (browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	else if (browserName.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	
	else if (browserName.equalsIgnoreCase("safari")) {
		WebDriverManager.safaridriver().setup();
		driver = new SafariDriver();
	} else {
    throw new IllegalArgumentException("The Browser Type is Undefined");
	}

	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	return driver;
}


	public void takingScreenshots(String testName, WebDriver driver) throws IOException {
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
	}
	}
