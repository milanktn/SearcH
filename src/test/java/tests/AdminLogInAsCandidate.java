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


public class AdminLogInAsCandidate {
	
	WebDriver driver;

	@Parameters({"Browser name", "URL","Admin email","Admin password"})
	@Test
	public void testAdminLogInAsCandidate(String browserName, String url, String adminEmail, String adminPassword) throws InterruptedException {
		
		
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
				
		
		
		driver.findElement(By.xpath("//p[contains(text(),'Candidates')]")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/select[1]")).click(); 
		
		driver.findElement(By.xpath("//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/select[1]/option[6]")).click();
		
		Thread.sleep(6000);
		
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/div[1]/button[1]")).click(); 	
		
		driver.findElement(By.xpath("//button[contains(text(),'Log in as a candidate')]")).click();
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[1]/a[1]")).click();
		
		//CAN BE CHANGED
		String loggedInAsCandidate = driver.findElement(By.xpath("//h2[contains(text(),'!Milan First')]")).getText();
		
		//CAN BE CHANGED
		Assert.assertEquals(loggedInAsCandidate, "!Milan First candidate");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
	
}

