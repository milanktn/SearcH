package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestList {
	
	@Parameters({"URL","Admin email","Admin password"})
	@Test
	public void testTestList(String url, String adminEmail, String adminPassword) throws InterruptedException {
		
		String browser = "chrome";
		
		WebDriver driver = null;
		
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			}
		
		else if(browser.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver();
			
			}
		
		else if(browser.equalsIgnoreCase("edge")) {
			
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
		
		
		List<WebElement> refNumber = driver.findElements(By.xpath(
				"//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/p[3]"));


		
		for(int i=0;i<5;)
		
		System.out.println(refNumber.size());
		
		/*
		int i = 0;
		
		while (i<id.get.){
			
			refNumber.get(i).getText()!="1678110200"
			
			System.out.println("poklapanje");
			i++;
			
			}
			 */
		
		driver.quit();
		
		}
	}
