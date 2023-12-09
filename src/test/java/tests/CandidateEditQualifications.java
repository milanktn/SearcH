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
												
public class CandidateEditQualifications {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Candidate email", "Password"})
	@Test
	public void testCandidateEditQualifications(String browserName, String url, String candidateEmail, String password) throws InterruptedException {

		
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
			

			driver.findElement(By.id("username")).sendKeys(candidateEmail);

			driver.findElement(By.id("password")).sendKeys(password);

			driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
				
			Thread.sleep(3000);
			
			
			driver.findElement(By.xpath(
					"//body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();

			driver.findElement(By.xpath("//p[contains(text(),'Profile')]")).click();
			
			
			driver.findElement(By.xpath(
					"//body/div[@id='root']/div[1]/div[3]/div[2]/div[4]/button[1]")).click();

			
			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"))
					.click();

			driver.findElement(By.id("react-select-2-option-3")).click();
			
			
			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"))
					.click();
			
			driver.findElement(By.id("react-select-3-option-0")).click();

			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"))
					.click();

			driver.findElement(By.id("react-select-5-option-3")).click();
			
			
			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"))
					.click();
			
			driver.findElement(By.id("react-select-4-option-1")).click();

			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"))
					.click();

			driver.findElement(By.id("react-select-6-option-2")).click();
			
			
			driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
			
			
			String candidatesProfileEdited = driver.findElement(By.xpath(
					"//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText();

			Assert.assertEquals(candidatesProfileEdited, "Profile updated successfully.");	
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
			