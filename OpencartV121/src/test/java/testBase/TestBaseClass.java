package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;									//We separated the testbase mainly because if it is in the test method class, it is difficult while we creating the XML file.
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseClass {

	
	public static WebDriver driver;							//this driver is static because we referred this class in Extent report class through object, so this driver and the driver in the extent report class will conflict
	public Logger logger;
	public Properties prop;
	
	
	@BeforeClass (groups="Master")
	@Parameters({"os","browser"})
	public void setUp (String os, String br) throws IOException {
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop= new Properties();
		prop.load(file);
		
		logger= LogManager.getLogger(this.getClass());				//this.getClass will get wherever it uses that current class 
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {			//if in the properties file, at execution_env if you give as remote, this will be executed
			
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
			switch(os.toLowerCase()) {
			case "windows" : capabilities.setPlatform(Platform.WIN11); break;
			case "mac" : capabilities.setPlatform(Platform.MAC); break;
			case "linux" : capabilities.setPlatform(Platform.LINUX); break;
			default : System.out.println("Invalid OS"); return;
			}
			
			switch (br.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser"); return;
			}
			
			driver= new RemoteWebDriver(new URL("http://172.20.10.5:4444/wd/hub"), capabilities);		//driver for remote driver
		}
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {			//if in the properties file, at execution_env if you give as local, this will be executed
			
			switch(br.toLowerCase()) {
			case "chrome":driver= new ChromeDriver(); break;
			case "edge":driver= new EdgeDriver(); break;
			case "safari" : driver= new SafariDriver(); break;
			default : System.out.println("Please provide the valid browser name"); return;
			}
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("appURL"));					//it will read the URL from config.properties file
		driver.manage().window().maximize();
	}
	
	
	
	@AfterClass (groups="Master")
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
	
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss"). format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"||screenshots||" + tname + " " + timeStamp;
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
