package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends TestBaseClass{
		
		
		@Test
		public void validation() {
			try {
			logger.info("**** Starting of TC001_AccountRegistrationTest ****");
			
			HomePage hp= new HomePage(driver);
			
				hp.ClickMyaccount();
				logger.info("**** Clicked on MyAccount ****");
				
				hp.ClickRegister();
				logger.info("**** Clicked on Register ****");
				
				
			AccountRegisterationPage regpage=new AccountRegisterationPage(driver);
			
			logger.info("**** Providing the customer details ****");
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
				
				if (confirmmsg.equals("Your Account Has Been Created!")) {
					Assert.assertTrue(true);
				}
				else {
					logger.error("Test failed..");
					Assert.assertTrue(false);
				}
						//Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
			}	//try
			
			catch(Exception e) {
				Assert.fail();
			}
			
			logger.info("**** Finished the TC001_AccountRegistrationTest");
		}
		
		
		
		
		
		
		/* Note:
		 * We have moved the setup method, teardown method, and some commonly used methods into testBaseClass.java
		 */
		
		
		
		

}

