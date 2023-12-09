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


public class AdminAddCompany {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL","Admin email","Admin password", "Company name reg", "Company website"})
	@Test
	public void testAdminAddCompany(String browserName, String url, String adminEmail, String adminPassword, String companyNameReg,
			String companyWebsite) {
		
		
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
		
		
		driver.findElement(By.xpath("//p[contains(text(),'Companies')]")).click();
		
		driver.findElement(By.linkText("+ Add company")).click();
		
		//CHANGE
		driver.findElement(By.id("name")).sendKeys(companyNameReg); 
		
		driver.findElement(By.id("webSiteUrl")).sendKeys(companyWebsite);
		
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		
		
		String addCompany = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText();
		
		Assert.assertEquals(addCompany, "Company successfully added.");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
