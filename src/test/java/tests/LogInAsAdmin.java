package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class LogInAsAdmin {

	WebDriver driver;
	
	@Parameters({"Browser name", "URL","Admin email","Admin password"})
	@Test
	public void testLogInAsAdmin(String browserName, String url, String adminEmail, String adminPassword) throws InterruptedException {

		driver = null;

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

		driver.get(url);

		driver.manage().window().maximize();
		

		driver.findElement(By.linkText("Log in")).click();
		

		driver.findElement(By.id("username")).sendKeys(adminEmail);

		driver.findElement(By.id("password")).sendKeys(adminPassword);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		

		Thread.sleep(18000);
		
		String clientsList = driver.findElement(By.xpath("//h2[contains(text(),'Clients')]")).getText();

		Assert.assertEquals(clientsList, "Clients");

	}
		
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}