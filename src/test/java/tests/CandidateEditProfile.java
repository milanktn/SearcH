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

public class CandidateEditProfile {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Candidate email", "Password", "Candidate first name", "Candidate last name",
		"Job title candidate", "CV", "LinkedIn", "Image2", "About me candidate", "About me candidateL2"})
	@Test
	public void testCandidateEditProfile(String browserName, String url, String candidateEmail, String password,
			String candidateFirstName, String candidateLastName, String jobTitleCandidate, String cv,
			String linkedIn, String image2, String aboutMeCandidate, String aboutMeCandidateL2) throws InterruptedException {

		
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
		
		Thread.sleep(4000);
		
		
		
		driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/button[1]"))
				.click();

		driver.findElement(By.xpath(
				"//p[contains(text(),'Profile')]"))
				.click();

		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"))
				.click();
		
		

		driver.findElement(By.id("firstName")).clear();

		driver.findElement(By.id("firstName")).sendKeys(candidateFirstName);

		driver.findElement(By.id("lastName")).clear();

		driver.findElement(By.id("lastName")).sendKeys(candidateLastName);

		driver.findElement(By.	id("jobTitle")).clear();

		driver.findElement(By.id("jobTitle")).sendKeys(jobTitleCandidate);

		driver.findElement(By.xpath(
				"//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/*[1]"))
				.click();

		driver.findElement(By.xpath(
				"//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/input[1]"))
				.sendKeys(cv);

		driver.findElement(By.id("linkedinProfile")).clear();

		driver.findElement(By.id("linkedinProfile")).sendKeys(linkedIn);

		//MUST HAVE IMAGE
		driver.findElement(By.xpath(
				"//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]"))
				.sendKeys(image2);

		driver.findElement(By.id("aboutMe")).clear();

		driver.findElement(By.id("aboutMe")).sendKeys(aboutMeCandidate);

		driver.findElement(By.id("aboutMe")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("aboutMe")).sendKeys(aboutMeCandidateL2);

		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

		
		String candidatesProfileEdited = driver.findElement(By.xpath(
				"//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]"))
				.getText();

		Assert.assertEquals(candidatesProfileEdited, "Profile updated successfully.");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}