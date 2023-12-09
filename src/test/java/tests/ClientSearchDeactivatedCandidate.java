package tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
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

//Ne radi
public class ClientSearchDeactivatedCandidate {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL", "Client email", "Password", "Admin email","Admin password" })
	@Test
	public void testClientSearchCandidateSameCompany(String browserName, String url, String clientEmail, String password, String adminEmail, String adminPassword
			) throws InterruptedException {

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
		driver.findElement(By.linkText("qaa")).click();
		
		Thread.sleep(5000);
		
		
		Set<String> set = driver.getWindowHandles();
		
		Iterator<String> itr = set.iterator();
		
		String mainWindow = itr.next();
		
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		
		Thread.sleep(6000);
		
		driver.close();
		
		driver.switchTo().window(mainWindow);
		
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//p[contains(text(),'Log out')]")).click();
		
		
		
		//Log in as administrator
		driver.findElement(By.id("username")).sendKeys(adminEmail);

		driver.findElement(By.id("password")).sendKeys(adminPassword);

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
				
		Thread.sleep(5000);
		
		//Doesn't work 
		driver.findElement(By.linkText("/admin/candidates")).click();
		
		
		//Deactivate
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/div[1]/button[2]")).click(); 
		
		driver.findElement(By.id("react-select-2-placeholder")).click();
		
		driver.findElement(By.id("react-select-2-option-0")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Deactivate a candidate')]")).click(); 
		
		String deactivate = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText(); 
		
		//CAN BE CHANGED
		Assert.assertEquals(deactivate, "!Milan First candidate is successfully deactivated.");
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.linkText("/logout")).click();
		
		
		//Log in as client again
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
		
		Actions actions1 = new Actions(driver);
		
		WebElement sliderLeft1 = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]"));
		
		actions1.dragAndDropBy(sliderLeft1, +50, 0).perform();
		
		WebElement sliderRight1 = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[3]"));
		
		actions1.dragAndDropBy(sliderRight1, -30, 0).perform();
		
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
		
	}
	
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
		