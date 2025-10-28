package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage{

		public AccountRegisterationPage(WebDriver driver) {
			super(driver);
			
		}
		
		
		@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
		@FindBy(xpath="//input[@id='input-lastname']")  WebElement txtlastname;
		@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
		@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
		@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
		@FindBy(xpath="//input[@id='input-confirm']")	WebElement txtConfirmPass;
		@FindBy(xpath="//input[@name='agree']") WebElement	ChkdPolicy;
		@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
		@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

		
		public void setFirstname(String fname) {
			txtFirstname.sendKeys(fname);
		}
		
		public void setLastname(String lname) {
			txtlastname.sendKeys(lname);
		}
		
		public void setEmail(String email) {
			txtEmail.sendKeys(email);
		}
		
		public void setTelephone(String telephone) {
			txtTelephone.sendKeys(telephone);
		}
		
		public void setPassword(String password) {
			txtPassword.sendKeys(password);
		}
		
		public void setConfirmPassword(String password) {
			txtConfirmPass.sendKeys(password);
		}
		
		public void setPrivacyPolicy() {
			ChkdPolicy.click();
		}
		
		public void clickContinue() {
			btnContinue.click();
		}
		
		public String getConfirmationMsg() {
			
			try {
				return (msgConfirmation.getText());				//we will validate in test method, If we got correct text, then try method will work, otherwise catch method will return the exception type to the console.
			}
			catch(Exception e) {
				return (e.getMessage());
			}
		}

}
