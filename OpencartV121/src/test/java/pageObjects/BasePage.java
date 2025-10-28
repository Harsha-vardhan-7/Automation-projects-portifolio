package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {			 

	
			WebDriver driver;
			
		public BasePage (WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		/* Note:
		 * This page will initiate the driver
		 * We will extends this page into every page
		 * This is a base page and will act as a parent page of all the page Object classes*/
	
}
