package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//Constructor
		public MyAccountPage(WebDriver driver) {
			super(driver);
		}
		
	//Locators
		@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgMyAccount;
		
	//Action method
		public boolean isMyAccountPageExists() {
			try {
				return msgMyAccount.isDisplayed();				//if it returns true, try method will be executed, and if it returns false we will get exception and catch method will be executed and then it will return false, with this, we will capture this boolean value and in test method we will validate 
			} catch (Exception e) {
				return false;
			}
		}

}
