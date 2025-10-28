package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	
	//Constructor
		public HomePage(WebDriver driver) {
			super(driver);
		}

	
	//Locators
		@FindBy(xpath="//a[@title='My Account']") WebElement lnkMyaccount;
		@FindBy (xpath= "//a[normalize-space()='Register']") WebElement lnkRegister;
		@FindBy (xpath= "//a[normalize-space()='Login']") WebElement lnkLogin;				//added in step 5
		
	//Action methods	
		public void ClickMyaccount() {
			lnkMyaccount.click();
		}
		
		public void ClickRegister() {
			lnkRegister.click();
		}
		
		public void ClickLogin() {				//added in step 5
			lnkLogin.click();
		}
}
