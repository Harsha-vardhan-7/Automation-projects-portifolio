package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBaseClass;

public class TC003_LoginDDT extends TestBaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)					//We mentioned "dataProviderClass" because, the data provider is in different package and different class
	public void verify_LoginDDT(String email, String pwd, String exp) {

			logger.info("**** Started TC003_LoginDDT ****");
			
		try {	
			HomePage hp= new HomePage(driver);
				hp.ClickMyaccount();
				hp.ClickLogin();
				
			LoginPage lp= new LoginPage(driver);
				lp.setEmailAddress(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
			MyAccountPage myAcc= new MyAccountPage(driver);
				boolean targetpage=myAcc.isMyAccountPageExists();
				
				
				logger.info("**** Validation started for exp value ****");
				
		if(exp.equalsIgnoreCase("Valid"))	{
			
			if (targetpage==true) {
				myAcc.ClickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}		//if
			
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetpage==true) {
				myAcc.ClickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}		//if
		
		}
		catch(Exception e) {
		    logger.error("Exception occurred during login test: " + e.getMessage());
		    logger.error("Exception details: ", e);
			Assert.assertTrue(false);
		}
		
		logger.info("**** Finished TC003_LoginDDT ****");
	}
}
