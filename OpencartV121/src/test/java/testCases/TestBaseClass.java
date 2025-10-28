package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseClass {

	
	WebDriver driver;
	
	@BeforeClass
	public void setUp () {
		
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	public String randomString() {
		String	generatedString=RandomStringUtils.randomAlphabetic(5);				//The strike-through is likely due to deprecation, meaning the method might be removed in future versions.
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber= RandomStringUtils.randomNumeric(9);
		return generatedNumber;
	}
	
	public String randomAplhaNumeric() {
		String	generatedString=RandomStringUtils.randomAlphabetic(5);
		String generatedNumber= RandomStringUtils.randomNumeric(9);
		return (generatedString+"@"+generatedNumber);
	}
}
