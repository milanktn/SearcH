package tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

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

public class MessagesPending {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email", "Password", "Job title candidate", "Message to candidate", "Message to candidateL2",
		"Candidate email"})
	@Test
	public void testMessagesPending(String browserName, String url, String clientEmail, String password, String jobTitleCandidate,
			String messageToCandidate, String messageToCandidateL2, String candidateEmail) throws InterruptedException {

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
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		
		
		//CAN BE IMPROVED MAYBE
		driver.findElement(By.linkText("QA Specialist")).click();
		
		Thread.sleep(4000);
		
		
		Set<String> set = driver.getWindowHandles();
		
		Iterator<String> itr = set.iterator();
		
		String mainWindow = itr.next();
		
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		
		Thread.sleep(4000);
		
		
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
		
		
		driver.findElement(By.xpath("//header/div[1]/a[2]/*[1]")).click();
		
		driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).click();

		Thread.sleep(2000);
		
		String chatPendingClient = driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[5]/div[1]/p[1]")).getText();

		Assert.assertEquals(chatPendingClient, "Chat request pending");
		
		
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[2]/header[1]/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//p[contains(text(),'Log out')]")).click();
		
		
		//Log in as candidate
		driver.findElement(By.id("username")).sendKeys(candidateEmail);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		Thread.sleep(4000);
		
		
		driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/div[1]/div[1]")).click();
		
		Thread.sleep(2000);
		
		String chatPendingCandidate = driver.findElement(By.xpath("//body/div[@id='root']/div[2]/div[1]/div[1]/div[5]/div[1]/p[1]")).getText();

		Assert.assertEquals(chatPendingCandidate, "Chat request pending");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}