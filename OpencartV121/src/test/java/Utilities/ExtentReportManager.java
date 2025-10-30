package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.TestBaseClass;

public class ExtentReportManager implements ITestListener{

	
		public ExtentSparkReporter sparkReporter; 				//deals with the UI of the report
		public ExtentReports extent;						//deals with some common info on the report. 
		public ExtentTest test;								//Deals with the operations like taking screenshot, updating the status of the test case in the report
		
		String repName;
	
		public void onStart(ITestContext testContext) {
			/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt=new Date() ;
			String currentdatetimestamp=df.format(dt);
			*/
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"). format(new Date());// time stamp
			repName = "Test-Report-" + timeStamp + ".html";
			sparkReporter = new ExtentSparkReporter("./reports/" + repName);	//specify's the path of where to save the file, name should end with .html
																													  		
			sparkReporter.config(). setDocumentTitle("Opencart Automation Report"); 		//Title of report
			 sparkReporter.config(). setReportName("Opencart Functional Testing"); 		//name of the report
		 sparkReporter.config(). setTheme(Theme.DARK);							//background theme of the report
			
			 extent=new ExtentReports();
			 extent.attachReporter (sparkReporter);
				
			 extent. setSystemInfo("Application","Opencart");				
			 extent. setSystemInfo("Module", "Admin"); 
			 extent.setSystemInfo("Sub Module", "Customers");
			 extent. setSystemInfo("User Name", System.getProperty("user.name"));
			 extent.setSystemInfo("Environment", "QA");
			 
			 String os = testContext.getCurrentXmlTest().getParameter("os");					//It will get the os parameter value from the XML file - "getCurrentXmlTest() - to get from xml file", "getParameter("os")- will get the value from the os parameter in xml file"
			 extent. setSystemInfo("Operating System", os) ;
			 
			 String browser = testContext.getCurrentXmlTest().getParameter("browser");			//It will get the browser parameter value from the XML file - "getCurrentXmlTest() - to get from xml file", "getParameter("browser")- will get the value from the browser parameter in xml file"
			 extent. setSystemInfo("Browser", browser);											//that browser value will set in this
			 
			 List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();			//It will get all the groups included in the XML file
			 if(!includedGroups.isEmpty()){																		
			 extent. setSystemInfo("Groups", includedGroups.toString());								//it will get the group name
				
		  }
		}
		
		
		public void onTestSuccess(ITestResult result) {
			test = extent. createTest(result.getTestClass().getName()); 					//A create a new entry in the report, and get the class name
			test. assignCategory(result.getMethod().getGroups ()); // to display groups in report
			test.log(Status.PASS, "Test case PASSED is:" + result.getName()); 	// update status p/f/s
		  }
		
		
		public void onTestFailure(ITestResult result) {
			test = extent. createTest(result.getTestClass().getName());					 //A create a new entry in the report, and get the class name
			test. assignCategory(result.getMethod().getGroups ()); 						// to display groups in report
			test.log(Status.FAIL, "Test case FAILED is:" + result.getName()); 	//update status p/f/s
			test.log(Status.INFO, "Test Case FAILED cause is: " + result.getThrowable().getMessage());		//will get the exception
			
			try {
				String imgPath = new TestBaseClass().captureScreen(result.getName());			//in base class, at captureScreen method, it will return the path of the screenshot, and because of this TestBaseClass object, we made driver in TestBaseClass as a static
				test. addScreenCaptureFromPath(imgPath);
				} catch (IOException e1) {
				e1.printStackTrace();
				}
		  }
		
		
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName ());
			test. assignCategory(result.getMethod().getGroups ()); 
			
			test.log(Status.SKIP, "Test case SKIPPED is: "+ result.getName());
			test.log(Status.INFO, "Test Case FAILED cause is: " + result.getThrowable().getMessage());
		 }
		
		
		public void onFinish(ITestContext context) {		
			extent.flush();										//It is mandatory to mention this
									
			/*String pathofExtentReport = System.getProperty("user.dir")+"\\reports||"+repName;
			File extentReport = new File(pathofExtentReport);
			try {
			Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
			e.printStackTrace();
			}*/
		}
	
	
	
}
