package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBaseClass;

public class TC002_LoginTest extends TestBaseClass{

	@Test
		public void verify_Login() {
			logger.info("**** Started TC002_LoginTest ****");
			
			try {
			HomePage hp= new HomePage(driver);
				hp.ClickMyaccount();
				hp.ClickLogin();
			
			LoginPage lp= new LoginPage(driver);
				lp.setEmailAddress(prop.getProperty("email"));
				lp.setPassword(prop.getProperty("password"));
				lp.clickLogin();
				
			MyAccountPage MyAcc= new MyAccountPage(driver);
			boolean	targetmessage=MyAcc.isMyAccountPageExists();
			
			Assert.assertTrue(targetmessage);					//if this is false, we will get exception, and if we get exception, catch block will execute and will shown as failed test case
					//Assert.assertEquals(targetmessage, true, "Login failed");				//we can either ways, in this the third parameter is - first will compare, if it is true, the both will be executed, but it is false, the third parameter will be executed
			}
			catch (Exception e) {
				Assert.fail();
			}
			
			logger.info("**** Finished TC002_LoginTest ****");
		}
		
}
