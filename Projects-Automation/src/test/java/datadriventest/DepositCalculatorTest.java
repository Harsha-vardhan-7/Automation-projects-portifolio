package datadriventest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DepositCalculatorTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");
		driver.manage().window().fullscreen();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String filepath = System.getProperty("user.dir") + "/src/test/resources/DDT1file.xlsx";

		int rows = UtilityFile.getRowCount(filepath, "Sheet1");

		// ✅ Read Excel file properly
		FileInputStream file = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		DataFormatter formatter = new DataFormatter();

		for (int r = 1; r <= rows; r++) { // r=1, because the actual value in the excel starts with 1, at index(0) there is header
			// 1)read data from excel
				/*
				 * String initialDep=UtilityFile.getCellData(filepath, "Sheet1", r, 0); String
				 * length=UtilityFile.getCellData(filepath, "Sheet1", r, 1); String
				 * interest=UtilityFile.getCellData(filepath, "Sheet1", r, 2); String
				 * compounding=UtilityFile.getCellData(filepath, "Sheet1", r, 3); String
				 * exp_total=UtilityFile.getCellData(filepath, "Sheet1", r, 4);
				 */

			// Using DataFormatter to get exact cell values as string, because toString is not working due to some format issue
			String initialDep = formatter.formatCellValue(sheet.getRow(r).getCell(0)); // with user-defined method in utility file, we can write like this - String initialDep= UtilityFile.getCellData2(filepath,"Sheet1", r, 0);
			String length = formatter.formatCellValue(sheet.getRow(r).getCell(1));
			String interest = formatter.formatCellValue(sheet.getRow(r).getCell(2));
			String compounding = formatter.formatCellValue(sheet.getRow(r).getCell(3));
			String exp_total = formatter.formatCellValue(sheet.getRow(r).getCell(4));

			// 2)passing data into the application
			WebElement intial_amt = driver.findElement(By.xpath("//input[@formcontrolname='cdAmount']"));
			intial_amt.clear();
			intial_amt.sendKeys(initialDep);

			WebElement month_lenght = driver.findElement(By.xpath("//input[@formcontrolname='cdLength']"));
			month_lenght.clear();
			month_lenght.sendKeys(length);

			WebElement interest_rate = driver.findElement(By.xpath("//input[@formcontrolname='cdRate']"));
			interest_rate.clear();
			interest_rate.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
			
			 for(char c : interest.toCharArray()) {
			 interest_rate.sendKeys(Character.toString(c)); //Types each character one by one
			 Thread.sleep(100); } 
			 
			 js.executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'))", interest_rate);
			 

			WebElement compound_button = driver.findElement(By.xpath("//div[@class='mat-mdc-select-trigger']"));
			js.executeScript("arguments[0].click()", compound_button);
			Thread.sleep(1000);
			WebElement drp_option = driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text' and contains(text(), '" + compounding + "')]"));
			js.executeScript("arguments[0].click()", drp_option);

			WebElement run_button = driver.findElement(By.xpath("//button[@id='CIT-chart-submit']"));
			js.executeScript("arguments[0].click()", run_button);

			Thread.sleep(2000);

			// 3)validation
			String totalvalue = driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();
			String cleanedTotal = totalvalue.replace("$", "").replace(",", "").trim();

			if (Double.parseDouble(cleanedTotal) == Double.parseDouble(exp_total)) {
				System.out.println("Test passed");
				UtilityFile.setCellData(filepath, "Sheet1", r, 6, "Pass");
				UtilityFile.fillGreenColor(filepath, "Sheet1", r, 6);
			} else {
				System.out.println("Test failed");
				UtilityFile.setCellData(filepath, "Sheet1", r, 6, "Fail");
				UtilityFile.fillRedColor(filepath, "Sheet1", r, 6);
			}

		} // ending of for loop

		workbook.close();
		file.close();
		driver.quit();

	}

}
