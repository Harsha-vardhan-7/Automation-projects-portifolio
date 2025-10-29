package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle cellstyle;
	String path;
	
		public ExcelUtility(String path) {
			this.path=path;
		}

	public int getRowCount(String sheetname) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount( String sheetname, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		int CellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return CellCount;
	}

	public String getCellData( String sheetname, int rowNum, int cellNum) throws IOException {			//using .toString method
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		String data;
		try {
			//data = cell.toString();
			/*
			 * We can get the value with this also There is no difference between toString
			 * and DataFormatter, we can use anyone.*/ 
			 DataFormatter formatter = new DataFormatter (); 
			  data = formatter.formatCellValue(cell); 				//Returns the formatted value of a cell as a String
			 
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fi.close();
		return data;
		// We put it in a try and catch block because, in middle if any block has a
		// blank, and there is no value in it, then we will get the exception and the
		// flow will be stopped,, so to handle that exception, we used try and catch
		// method.

	}
		
	public String getCellData2( String sheetname, int rowNum, int cellNum) throws IOException {			//use when data formatting is required
	    FileInputStream fi = new FileInputStream(path);
	    XSSFWorkbook workbook = new XSSFWorkbook(fi);
	    XSSFSheet sheet = workbook.getSheet(sheetname);
	    XSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);

	    DataFormatter formatter = new DataFormatter();
	    String data = "";
	    try {
	        data = formatter.formatCellValue(cell);
	    } catch (Exception e) {
	        data = "";
	    }

	    workbook.close();
	    fi.close();
	    return data;
	}
	

	public void setCellData( String sheetname, int rowNum, int cellNum, String data)
			throws IOException { // we used void, because we are creating the workbook and this will not return
									// any value
		
		File xlfile= new File (path);
		
		if(!xlfile.exists()) {					//If the file is not exists, it will create a new file
			workbook= new XSSFWorkbook();
			fo= new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path); // this setCellData will be used while filing the results data into the excel(mostly)
		workbook = new XSSFWorkbook(fi);
		
		if (workbook.getSheetIndex(sheetname)==-1) // If sheet not exists then create new Sheet
			workbook. createSheet (sheetname); 
		sheet=workbook.getSheet(sheetname);
		
		if(sheet.getRow(rowNum)==null) // If row not exists then create new Row
			sheet. createRow(rowNum);
			row=sheet.getRow(rowNum);

		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	public void fillGreenColor(String filepath, String sheetname, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		cellstyle = workbook.createCellStyle();

		cellstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cellstyle);

		fo = new FileOutputStream(filepath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String filepath, String sheetname, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		cellstyle = workbook.createCellStyle();

		cellstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cellstyle);

		fo = new FileOutputStream(filepath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
}
