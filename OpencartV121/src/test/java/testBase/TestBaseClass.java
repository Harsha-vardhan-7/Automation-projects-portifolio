package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;									//We separated the testbase mainly because if it is in the test method class, it is difficult while we creating the XML file.
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseClass {

	
	public WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setUp (String os, String br) throws IOException {
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop= new Properties();
		prop.load(file);
		
		logger= LogManager.getLogger(this.getClass());				//this.getClass will get wherever it uses that current class 
		
		switch(br.toLowerCase()) {
		case "chrome":driver= new ChromeDriver(); break;
		case "edge":driver= new EdgeDriver(); break;
		case "safari" : driver= new SafariDriver(); break;
		default : System.out.println("Please provide the valid browser name"); return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("appURL"));					//it will read the URL from config.properties file
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
