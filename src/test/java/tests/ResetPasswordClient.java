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

public class ResetPasswordClient {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email"})
	@Test
	public void testResetPasswordClient(String browserName, String url, String clientEmail) {


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
		
		
		driver.findElement(By.xpath("//a[contains(text(),'Forgot password?')]")).click();

		driver.findElement(By.id("email")).sendKeys(clientEmail);

		driver.findElement(By.xpath("//button[contains(text(),'Send reset instructions')]")).click();

		
		String checkInbox = driver.findElement(By.xpath("//h2[contains(text(),'Check your inbox')]")).getText();

		Assert.assertEquals(checkInbox, "Check your inbox");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
