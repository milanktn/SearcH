package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

	WebDriver driver;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="username")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	private WebElement logInButton;
	
	@FindBy(xpath="//body[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[2]/p[1]/span[1]")
	private WebElement logInFailedMessage;
	
	
	
	public WebElement emailField() {
		return emailField;
	}
	
	public WebElement passwordField() {
		return passwordField;
	}
	
	public WebElement logInButton() {
		return logInButton;
	}
	
	public WebElement logInFailedMessage() {
		return logInFailedMessage;
	}
}
