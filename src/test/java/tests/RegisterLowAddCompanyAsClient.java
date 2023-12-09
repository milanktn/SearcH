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

public class RegisterLowAddCompanyAsClient {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client first name", "Client last name reg", 
		"Company name reg", "Company website", "Client email reg", "Phone", "Password", })
	@Test
	public void testRegisterLowAddCompanyAsClient(String browserName, String url, String clientFirstName, String clientLastNameReg,
			String companyNameReg, String companyWebsite, String clientEmailReg, String phone, String password) throws InterruptedException {


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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);

		driver.manage().window().maximize();
		

		driver.findElement(By.linkText("Get started")).click();

		driver.findElement(By.linkText("Join as a company")).click();
		
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[4]/a[1]")).click();
		
		
		driver.findElement(By.id("firstName")).sendKeys(clientFirstName);

		// CHANGE
		driver.findElement(By.id("lastName")).sendKeys(clientLastNameReg); 

		driver.findElement(By.id("react-select-2-input")).click();

		driver.findElement(By.className("menu-btn")).click();

		// CHANGE
		driver.findElement(By.id("companyName")).sendKeys(companyNameReg); 

		driver.findElement(By.id("companyWebSiteUrl")).sendKeys(companyWebsite);

		driver.findElement(By.xpath("//button[contains(text(),'Add company')]")).click();

		Thread.sleep(3000);
		
		driver.findElement(By.id("email")).click();

		// CHANGE
		driver.findElement(By.id("email")).sendKeys(clientEmailReg); 

		driver.findElement(By.id("phoneNumber")).sendKeys(phone);

		driver.findElement(By.id("plainPassword")).sendKeys(password);

		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/label[1]/span[1]")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Create account')]")).click();

		
		String thankYou = driver.findElement(By.xpath("//h2[contains(text(),'Thank you!')]")).getText();

		Assert.assertEquals(thankYou, "Thank you!");
	}

	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
