package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//CAN BE IMPROVED

public class AdminLogInAsClient {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL","Admin email","Admin password"})
	@Test
	public void testAdminLogInAsClient(String browserName, String url, String adminEmail, String adminPassword) throws InterruptedException {
		
		
		driver = null;
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver();
			
			}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			
			 driver = new EdgeDriver();
			 
			 }
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get(url);
	
		driver.manage().window().maximize();
		
		
		driver.findElement(By.linkText("Log in")).click();
		
		
		driver.findElement(By.id("username")).sendKeys(adminEmail);
		
		driver.findElement(By.id("password")).sendKeys(adminPassword);
		
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
				
			
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[8]/button[1]")).click();
		
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/div[1]/button[1]")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Log in as a client')]")).click();

		
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[1]/a[1]")).click();
		
		//CAN BE CHANGED
		String loggedInAsClient = driver.findElement(By.xpath("//h2[contains(text(),'Ana Fisher')]")).getText(); 

		//CAN BE CHANGED
		Assert.assertEquals(loggedInAsClient, "Ana Fisher");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
	
}

