package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//Constructor
		public LoginPage(WebDriver driver) {
			super(driver);
		}
		
	//Locators
		@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
		@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
		@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
		
	//Action methods
		public void setEmailAddress(String email) {
			txtEmailAddress.clear();
			txtEmailAddress.sendKeys(email);
		}
		
		public void setPassword(String password) {
			txtPassword.clear();
			txtPassword.sendKeys(password);
		}
		
		public void clickLogin() {
			btnLogin.click();
		}
		
}
	



