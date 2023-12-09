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


public class AdminDeactivateActivateCandidate {

	//TEST SHOULD BE IMPROVED(SELECT CANDIDATES AND CHECK NAMES)
	
	WebDriver driver;

	@Parameters({"Browser name", "URL","Admin email","Admin password"})
	@Test
	public void testAdminDeactivateActivateCandidate(String browserName, String url, String adminEmail, String adminPassword) throws InterruptedException {
		
		
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

		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/select[1]")).click(); 
			
		driver.findElement(By.xpath("//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/select[1]/option[6]")).click();
		
		
		
		//Deactivate
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/div[1]/button[2]")).click(); 
		
		driver.findElement(By.id("react-select-2-placeholder")).click();
		
		driver.findElement(By.id("react-select-2-option-0")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Deactivate a candidate')]")).click(); 
		
		String deactivate = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText(); 
		
		//CAN BE CHANGED
		Assert.assertEquals(deactivate, "!Milan First candidate is successfully deactivated.");
		
		Thread.sleep(3000);
		
		
		//Activate
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/div[1]/button[2]")).click();
		
		
		String activate = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText(); 
		
		//CAN BE CHANGED
		Assert.assertEquals(activate, "!Milan First candidate is successfully activated.");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
