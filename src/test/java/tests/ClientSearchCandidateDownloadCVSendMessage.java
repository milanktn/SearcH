package tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClientSearchCandidateDownloadCVSendMessage {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email", "Password", "Job title candidate", "Message to candidate", "Message to candidateL2"})
	@Test
	public void testClientSearchCandidateDownloadCVSendMessage(String browserName, String url, String clientEmail, String password, String jobTitleCandidate,
			String messageToCandidate, String messageToCandidateL2) throws InterruptedException {

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get(url);

		driver.manage().window().maximize();
		

		driver.findElement(By.linkText("Log in")).click();
		

		driver.findElement(By.id("username")).sendKeys(clientEmail);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		Thread.sleep(4000);
		
		
		//Job family
		driver.findElement(By.id("react-select-2-placeholder")).click();
		
		driver.findElement(By.id("react-select-2-option-0")).click();
		
		driver.findElement(By.id("react-select-2-option-1")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		//Qualifications
		driver.findElement(By.id("react-select-3-placeholder")).click();
		
		driver.findElement(By.id("react-select-3-option-0")).click();
		
		driver.findElement(By.id("react-select-3-option-1")).click();
		
		driver.findElement(By.id("react-select-3-option-2")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		//Degree
		driver.findElement(By.id("react-select-4-placeholder")).click();
		
		driver.findElement(By.id("react-select-4-option-0")).click();
		
		driver.findElement(By.id("react-select-4-option-1")).click();
		
		driver.findElement(By.id("react-select-4-option-2")).click();
		
		driver.findElement(By.id("react-select-4-option-3")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		//Salary (slider)
		
		//Open slider
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]"))
				.click();
		
		Actions actions = new Actions(driver);
		
		WebElement sliderLeft = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]"));
		
		actions.dragAndDropBy(sliderLeft, +50, 0).perform();
		
		WebElement sliderRight = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[3]"));
		
		actions.dragAndDropBy(sliderRight, -30, 0).perform();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		
		//Sector
		driver.findElement(By.id("react-select-5-placeholder")).click();
		
		driver.findElement(By.id("react-select-5-option-0")).click();
		
		driver.findElement(By.id("react-select-5-option-1")).click();
		
		driver.findElement(By.id("react-select-5-option-2")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		
		//Location
		driver.findElement(By.id("react-select-6-placeholder")).click();
		
		driver.findElement(By.id("react-select-6-option-0")).click();
		
		driver.findElement(By.id("react-select-6-option-1")).click();
		
		driver.findElement(By.id("react-select-6-option-2")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		
		
		//CAN BE IMPROVED MAYBE
		driver.findElement(By.linkText("QA Specialist")).click();
		
		Thread.sleep(5000);
		
		
		Set<String> set = driver.getWindowHandles();
		
		Iterator<String> itr = set.iterator();
		
		String mainWindow = itr.next();
		
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		
		Thread.sleep(5000);
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Download CV')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Message')]")).click();
		
		
		driver.findElement(By.id("jobTitle")).sendKeys(jobTitleCandidate);
		
		driver.findElement(By.id("message")).clear();
		
		driver.findElement(By.id("message")).sendKeys(messageToCandidate);
		
		driver.findElement(By.id("message")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("message")).sendKeys(messageToCandidateL2);
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
		
		String messageSent = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText();

		Assert.assertEquals(messageSent, "Message sent successfully.");
		
		driver.close();
		
		driver.switchTo().window(mainWindow);
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
	
}