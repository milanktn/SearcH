package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CandidateChatPage;
import pageObjects.CandidateProfilePage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import resources.Base;


public class LogInAsCandidate extends Base {
	
	public WebDriver driver;
	
	@Test
	public void testLogInAsCandidate() throws InterruptedException, IOException {

		HomePage homePage = new HomePage(driver);
		homePage.logInButton().click();
		
		LogInPage logInPage = new LogInPage(driver);
		logInPage.emailField().sendKeys(prop.getProperty("candidateEmail"));
		logInPage.passwordField().sendKeys(prop.getProperty("password4all"));
		logInPage.logInButton().click();
		Thread.sleep(3000);
		
		CandidateChatPage candidateChatPage = new CandidateChatPage(driver);
		candidateChatPage.profileDropdown().click();
		candidateChatPage.profileOption().click();
		Thread.sleep(3000);
		
		CandidateProfilePage candidateProfilePage = new CandidateProfilePage(driver);
		String candidateName = candidateProfilePage.candidateName().getText();
		
		//CAN BE CHANGED, MAYBE IMPROVED
		Assert.assertEquals(candidateName, "Milan Candidate1");
	}
	
	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod	
	public void quit() {
		driver.quit();
	}
}