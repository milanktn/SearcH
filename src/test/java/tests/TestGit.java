package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.Base;

public class TestGit extends Base{
	
	public WebDriver driver;
	
  @Test
  public void testTestGit() {
	  
	  HomePage homePage = new HomePage(driver);
	  homePage.logInButton().click();
	  System.out.print("Da se puluje");	
	  System.out.print("Testiraju se grane"); 
	  System.out.print("Nova grana");
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
