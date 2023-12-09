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


public class AdminAddCandidateAddCompany {
	
	WebDriver driver;
	
	@Parameters({"Browser name", "URL","Admin email","Admin password", "Candidate first name", "Candidate last name reg",
		"Job title candidate", "CV", "Candidate email reg", "LinkedIn", "Image2", "Salary", "Company name reg", "Company website"})
	@Test
	public void testAdminAddCandidateAddCompany(String browserName, String url, String adminEmail, String adminPassword, String candidateFirstName,
			String candidateLastNameReg, String jobTitleCandidate, String cv, String candidateEmailReg,
			String linkedIn, String image2, String salary, String companyNameReg, String companyWebsite) throws InterruptedException {
	
		
		driver = null;
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver();
			
			}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			
			 driver = new EdgeDriver();
			 
			 }
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get(url);
	
		driver.manage().window().maximize();
		
		
		driver.findElement(By.linkText("Log in")).click();
		
		
		driver.findElement(By.id("username")).sendKeys(adminEmail);
		
		driver.findElement(By.id("password")).sendKeys(adminPassword);
		
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		
		driver.findElement(By.xpath("//p[contains(text(),'Candidates')]")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'+ Add candidate')]")).click();
		
		
		
		//Basic info
		
		driver.findElement(By.name("firstName")).sendKeys(candidateFirstName);
		
		//CHANGE
		driver.findElement(By.name("lastName")).sendKeys(candidateLastNameReg);  
		
		driver.findElement(By.name("currentJobTitle")).sendKeys(jobTitleCandidate);
		
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/input[1]"))
		.sendKeys(cv);
		
		//CHANGE
		driver.findElement(By.name("email")).sendKeys(candidateEmailReg);  
		
		driver.findElement(By.id("react-select-2-placeholder")).click();
		
		driver.findElement(By.id("react-select-2-option-2")).click();
		
		driver.findElement(By.id("react-select-2-option-9")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		driver.findElement(By.name("linkedinProfile")).sendKeys(linkedIn);
		
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/div[7]/div[1]/div[1]/div[1]/input[1]"))
		.sendKeys(image2);
		
		driver.findElement(By.className("continueBtn")).click();
		
		
		
		//Job preferences
		
		driver.findElement(By.id("react-select-3-placeholder")).click();
		
		driver.findElement(By.id("react-select-3-option-2")).click();
		
		driver.findElement(By.id("react-select-3-option-9")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		driver.findElement(By.id("requiredSalary")).sendKeys(salary);
		
		driver.findElement(By.className("continueBtn")).click();
		
		
		
		//Experience
		
		driver.findElement(By.id("react-select-4-input")).click();
		
		driver.findElement(By.className("menu-btn")).click();
		
		//CHANGE
		driver.findElement(By.id("companyName")).sendKeys(companyNameReg); 
		
		driver.findElement(By.id("companyWebSiteUrl")).sendKeys(companyWebsite);
		
		driver.findElement(By.xpath("//button[contains(text(),'Add company')]")).click();	
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("react-select-5-placeholder")).click();
		
		driver.findElement(By.id("react-select-5-option-3")).click();
		
		driver.findElement(By.id("react-select-6-placeholder")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("react-select-6-option-0")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
		
		driver.findElement(By.className("continueBtn")).click();
		
		
		
		//Qualifications
		
		driver.findElement(By.id("react-select-7-placeholder")).click();
		
		driver.findElement(By.id("react-select-7-option-2")).click();
		
		driver.findElement(By.id("react-select-8-placeholder")).click();
		
		driver.findElement(By.id("react-select-8-option-5")).click();
		
		driver.findElement(By.id("react-select-9-placeholder")).click();
		
		driver.findElement(By.id("react-select-9-option-4")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'+ Add another')]")).click();
		
		driver.findElement(By.id("react-select-10-placeholder")).click();
		
		driver.findElement(By.id("react-select-10-option-2")).click();
		
		driver.findElement(By.id("react-select-11-placeholder")).click();
		
		driver.findElement(By.id("react-select-11-option-2")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Finish')]")).click();
		
		
		String candidateCreated = driver.findElement(By.xpath("//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")).getText();
		
		Assert.assertEquals(candidateCreated, "Candidate added successfully.");
	}
	
	@AfterMethod	
	public void quit() {
		
		driver.quit();
	}
}
