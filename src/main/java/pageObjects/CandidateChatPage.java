package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CandidateChatPage {
	
WebDriver driver;
	
	public CandidateChatPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//html[1]/body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/button[1]")
	private WebElement profileDropdown;
	
	@FindBy(xpath="//p[contains(text(),'Profile')]")
	private WebElement profileOption;
	
	@FindBy(xpath="//h2[contains(text(),'All chats')]")
	private WebElement allChatsText;
	
	
	public WebElement profileDropdown() {
		return profileDropdown;
	}
	
	public WebElement profileOption () {
		return profileOption;
	}
	
	public WebElement allChatsText() {
	return allChatsText;
	}
}

