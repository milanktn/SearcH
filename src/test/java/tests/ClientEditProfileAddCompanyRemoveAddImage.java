package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClientEditProfileAddCompanyRemoveAddImage {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email", "Password", "Client first name", "Client last name",
		"Job title client", "Company name reg2", "Company website", "LinkedIn", "Image", "About me client", "About me clientL2"})
	@Test
	public void testClientEditProfileAddCompanyRemoveAddImage(String browserName, String url, String clientEmail, String password,
			String clientFirstName, String clientLastName, String jobTitleClient, String companyNameReg2, String companyWebsite,
			String linkedIn, String image, String aboutMeClient, String aboutMeClientL2) throws InterruptedException {

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
		

		driver.findElement(By.linkText("Log in")).click();
		

		driver.findElement(By.id("username")).sendKeys(clientEmail);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();

		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[1]/a[1]")).click();

		driver.findElement(By.className("linkBtnIconHover")).click();

		driver.findElement(By.id("firstName")).clear();

		driver.findElement(By.id("firstName")).sendKeys(clientFirstName);

		driver.findElement(By.id("lastName")).clear();

		driver.findElement(By.id("lastName")).sendKeys(clientLastName);

		driver.findElement(By.id("jobTitle")).clear();

		driver.findElement(By.id("jobTitle")).sendKeys(jobTitleClient);

		driver.findElement(By.xpath(
				"//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]")).click();

		driver.findElement(By.className("menu-btn")).click();

		// CHANGE
		driver.findElement(By.id("newCompanyName")).sendKeys(companyNameReg2); 

		driver.findElement(By.id("companyWebSiteUrl")).sendKeys(companyWebsite);

		driver.findElement(By.xpath("//button[contains(text(),'Add company')]")).click();

		Thread.sleep(2000);

		driver.findElement(By.id("linkedinProfile")).clear();

		driver.findElement(By.id("linkedinProfile")).sendKeys(linkedIn);

		//MUST HAVE IMAGE
		driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]"))
				.sendKeys(image);

		driver.findElement(By.id("aboutMe")).clear();

		driver.findElement(By.id("aboutMe")).sendKeys(aboutMeClient);

		driver.findElement(By.id("aboutMe")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("aboutMe")).sendKeys(aboutMeClientL2);

		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		

		String clientsProfileEdit = driver.findElement(By.xpath(
				"//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText();

		Assert.assertEquals(clientsProfileEdit, "Profile updated successfully.");
	}

	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
	
}
