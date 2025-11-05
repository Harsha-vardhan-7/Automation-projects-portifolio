package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Searchpage extends BasePage{

		public Searchpage(WebDriver driver) {
			super(driver);
			
		}
		
		
		@FindBy(xpath="//input[@placeholder='Search']") WebElement txtSearchbar;
		@FindBy(xpath="//button[@class='btn btn-default btn-lg']") WebElement btnSearch;
		@FindBy(xpath="//button[contains(@onclick,'cart.add')]") WebElement btnAddtocart;
		@FindBy(xpath="//a[@title='Shopping Cart']") WebElement btnShoppingcart;
		
		
		public void SearchInp (String productname) {
			txtSearchbar.sendKeys(productname);
		}
		
		public void SearchBtn() {
			btnSearch.click();
		}
		
		public boolean isProductDisplayed(String productName) {
	        try {
	            return driver.findElement(By.xpath("//div[@class='caption']//a[contains(text(),'" + productName + "')]")).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
				
		public void AddtoCart() {
			btnAddtocart.click();
		}
		
		public void ShoppingCart() {
			btnShoppingcart.click();
		}
		
		
		

}
