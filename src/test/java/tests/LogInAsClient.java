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

public class LogInAsClient {
	
	public WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email", "Password"})
	@Test
	public void testLogInAsClient(String browserName, String url, String clientEmail, String password) throws InterruptedException {

		
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
		

		driver.findElement(By.id("username")).sendKeys(clientEmail);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

		Thread.sleep(3000);
		
		

		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();

		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[1]/a[1]")).click();
		
		
		// CAN BE CHANGED, MAYBE IMPROVED
		String clientFirstAndLastName = driver.findElement(By.xpath("//h2[contains(text(),'Milan Client1')]")).getText(); 

		//CAN BE CHANGED, MAYBE IMPROVED
		Assert.assertEquals(clientFirstAndLastName, "Milan Client1");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
