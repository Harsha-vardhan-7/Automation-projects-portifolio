package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTes extends TestBaseClass{
		
		
		@Test
		public void validation() {
			
			HomePage hp= new HomePage(driver);
			
				hp.ClickMyaccount();
				hp.ClickRegister();
				
			AccountRegisterationPage regpage=new AccountRegisterationPage(driver);
				regpage.setFirstname(randomString());
				regpage.setLastname(randomString());
				regpage.setEmail(randomString()+ "@gmail.com");
				regpage.setTelephone(randomNumber());
				
				String 	password=randomAplhaNumeric();
				regpage.setPassword(password);
				regpage.setConfirmPassword(password);
				
				regpage.setPrivacyPolicy();
				regpage.clickContinue();
				String confirmmsg=regpage.getConfirmationMsg();
				
				Assert.assertEquals(confirmmsg,	"Your Account Has Been Created!");
		}
		
		
		
		
		
		

}

