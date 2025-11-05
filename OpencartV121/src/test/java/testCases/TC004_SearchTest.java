package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.Searchpage;
import testBase.TestBaseClass;

public class TC004_SearchTest extends TestBaseClass {

	
		
	@Test	
		public void SearchTest() {
		
		logger.info("=== Starting Test: TC004_SearchTest ===");
		
		
		try {
		LoginPage lp= new LoginPage(driver);
		
		logger.info("Logging into the application...");
		lp.setEmailAddress(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		String productName="iphone";
		
		Searchpage sp= new Searchpage(driver);
		
		logger.info("Searching for product: "+ productName);
			sp.SearchInp(productName);
			sp.SearchBtn();
			
		logger.info("Verifying if product is displayed...");
			
			if(sp.isProductDisplayed(productName)) {
				logger.info("Adding product to cart...");
				sp.AddtoCart();
				logger.info("Navigating to shopping cart...");
				sp.ShoppingCart();
			}
			else {
				logger.error("Product not displayed in search results.");
				Assert.fail("Product not displayed in search results");
				}
			}
		
			catch(Exception e) {
				
				logger.error("Exception occurred during SearchTest: ", e);
				Assert.fail("Exception occured during the flow: "+ e.getMessage());
			}
		
			finally{
			
			logger.info("=== Finished Test: TC004_SearchTest ===");
			}
	}
}	
		
