package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners extends TestBaseClass implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListeners.class);

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            File targetFile = new File(System.getProperty("user.dir") + "/Screenshots/" + testName +"_"+timeStamp+ ".png");

            // Ensure directory exists
            File parentDir = targetFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileUtils.copyFile(sourceFile, targetFile);
            logger.error("Screenshot captured for failed test: " + testName);
            
        } catch (IOException e) {
            logger.error("Failed to capture screenshot for " + testName + ": " + e.getMessage());
            
        } catch (Exception e) {
            logger.error("Unexpected error while capturing screenshot: " + e.getMessage());
        }
    }
}
