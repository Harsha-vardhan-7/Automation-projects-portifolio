package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

		@DataProvider(name="LoginData")
		public Object[][] getData() throws IOException {
			
			String path= "./testData/Opencart_LoginData.xlsx";		//Will takes the data from the excel which present in this path
			
			ExcelUtility xlutil = new ExcelUtility(path);				//creating an object for ExcelUtility to get the data
				
				int totalrows= xlutil.getRowCount("Sheet1");			//for total rows
				int totalcols= xlutil.getCellCount("Sheet1", 1);		//for total columns, these are because we will give same no of count for the two dimension to get the data
				
				String logindata[][]= new String [totalrows][totalcols];		//created for two dimensional array which can store the login data (username,password)
				
				for (int r=1; r<=totalrows; r++) {				//started from 1, because in excel first row we will always have header
					
					for (int c=0; c<totalcols; c++) {			
						logindata[r-1][c]= xlutil.getCellData("Sheet1", r, c);				//r-1 because, we started in for loop with 1 and count will get extra, but in two dimension the index will starts from 0
					}
				}
				
				return logindata;			//returns the two dimensional array
			
		}
}
