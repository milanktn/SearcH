package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CandidateChatPage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import resources.Base;


public class LogInAsCandidateDataProvider extends Base {
	
	public WebDriver driver;
	
	@Test(dataProvider="getLogInData")
	public void testLogInAsCandidateDataProvider(String email, String password, String expectedResult) throws InterruptedException, IOException {

		
		HomePage homePage = new HomePage(driver);
		homePage.logInButton().click();
		
		LogInPage logInPage = new LogInPage(driver);
		logInPage.emailField().sendKeys(email);
		logInPage.passwordField().sendKeys(password);
		logInPage.logInButton().click();
		Thread.sleep(3000);
				
		CandidateChatPage candidateChatPage = new CandidateChatPage(driver);
		String actuallResult = null;
		try {
			if(candidateChatPage.allChatsText().isDisplayed()) {
			actuallResult = "Successful";
			}
		} catch(Exception e) {
			actuallResult = "Failure";
		}
		Assert.assertEquals(actuallResult, expectedResult);
	}
	
	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod	
	public void quitBrowser() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getLogInData () {
		Object[][] data = {{"milan.kitanovic+candidate1@etondigital.com","Milanmilan1?","Successful"},{"nekimail@gmail.com","nekipass","Failure"}};
		return data;
	}
}
