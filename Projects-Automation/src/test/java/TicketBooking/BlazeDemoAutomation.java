package TicketBooking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BlazeDemoAutomation {

	public static void main(String[] args) {
		/*# BlazeDemo Flight Automation					//Selenium-FlightBooking

		This project automates flight booking on https://blazedemo.com/ using Selenium WebDriver with Java. 
		Features:
		- Select departure and destination cities
		- Read flight table and select the lowest-priced flight
		- Fill passenger and payment details
		- Validate successful purchase message
		*/
		
		
			WebDriver driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			driver.get("https://blazedemo.com/");
				driver.manage().window().maximize();
				
				
				
				WebElement fromcity=driver.findElement(By.xpath("//select[@name=\'fromPort']"));
						Select fromcityopt= new Select(fromcity);
						fromcityopt.selectByVisibleText("Boston");
						
						
				WebElement tocity=driver.findElement(By.xpath("//select[@name=\'toPort']"));
						Select tocityopt= new Select(tocity);
						tocityopt.selectByVisibleText("London");
						
				//find flight button		
					driver.findElement(By.xpath("//input[@value='Find Flights']")).click();		
					
					
					
				//Handling table	
					driver.findElement(By.xpath("//table[@class='table']"));			
					
					 int rows= driver.findElements(By.xpath("//table[@class='table']//tr")).size();
					 	System.out.println("Number of rows: "+rows);
					 	
					 // Variables to track lowest price and its row 
					 	int minPrice = Integer.MAX_VALUE; 
					 	int minRow = -1;
					
					 // Loop through rows and find lowest price 
					 	for (int r = 2; r < rows; r++) { 								// starting from 2 to skip header row 
						 		String price = driver.findElement(By.xpath("//table[@class='table']//tr[" + r + "]//td[6]")).getText(); 
						 		
						 		int prices = Integer.parseInt(price.replaceAll("[^0-9]", "")); 
						 		
						 		if (prices < minPrice) {
					 				minPrice = prices; 
					 				minRow = r; 
					 			
					 			} 
					 		}
					 	System.out.println("Lowest price: " + minPrice + " found at row: " + minRow);
					 	
					 	
					 	if (minRow != -1) { 
					 		driver.findElement(By.xpath("//table[@class='table']//tr[" + minRow + "]//input[@value='Choose This Flight']")).click(); 
					 		} else { 
					 			System.out.println("No flight rows found!"); 
					 			driver.quit();
					 			return; }
					 	
							
							
							
								//Name
									driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys("Harsha");
									
								//Address
									driver.findElement(By.xpath("//input[@id='address']")).sendKeys("5-111, main road");
									
								//City
									driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Mandapeta");
									
								//State
									driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Andhra pradesh");
									
								//Zipcode
									driver.findElement(By.xpath("//input[@id='zipCode']")).sendKeys("533309");
									
								//Card type
								WebElement cardtype=driver.findElement(By.xpath("//select[@id='cardType']"));
									Select typecard= new Select(cardtype);
									
									typecard.selectByVisibleText("American Express");
									
								//Credit card number
									driver.findElement(By.xpath("//input[@id='creditCardNumber']")).sendKeys("1234 1234 1234 1234");
									
								//Credit card month
									driver.findElement(By.xpath("//input[@id='creditCardMonth']")).sendKeys("01");
									
								//credit card year
									driver.findElement(By.xpath("//input[@id='creditCardYear']")).sendKeys("2026");
									
								//name on card
									driver.findElement(By.xpath("//input[@id='nameOnCard']")).sendKeys("Harsha");
									
								//remember me check box
									driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
									
								//purchase flight
									driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
									
									
							//Getting the thanks message
								String thanksmsg=driver.findElement(By.xpath("//div[@class='container hero-unit']//h1")).getText();
								System.out.println(thanksmsg);

	}

}
