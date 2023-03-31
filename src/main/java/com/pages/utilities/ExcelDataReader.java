package com.pages.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelDataReader {
	
	private static ExcelDataReader reader = null;
	private static XSSFSheet sheet;
	
	public static ExcelDataReader getInstance(String dataFile,String sheetName) throws IOException {
		reader = new ExcelDataReader(dataFile,sheetName);
		return reader;
	}
	
	public static ExcelDataReader getInstance(String dataFile) throws IOException {
		reader = new ExcelDataReader(dataFile);
		return reader;
	}
	public static void setSheet(XSSFSheet sheet1) {
		sheet = sheet1;
	}
	private ExcelDataReader(String dataFile) throws IOException{
		XSSFSheet sheet1 = null;
		String filePath = "src\\test\\resources\\properties\\"+dataFile+".xlsx";
		try(FileInputStream fis = new FileInputStream(filePath)){
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				sheet1 = wb.getSheetAt(0);
			}
		} catch (IOException e) {
			LoggerProperties.getInstance().getLogger().info(e);
		}
		setSheet(sheet1);
	}
	
	private ExcelDataReader(String dataFile, String sheetName) throws IOException{
		XSSFSheet sheet1 = null;
		String filePath = "src\\test\\resources\\properties\\"+dataFile+".xlsx";
		try(FileInputStream fis = new FileInputStream(filePath)){
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				sheet1 = wb.getSheet(sheetName);
			}
		} catch (IOException e) {
			LoggerProperties.getInstance().getLogger().info(e);
		}
		setSheet(sheet1);
	}
	
	
	public static List<String> getRowValues(int rowNumber) {
		XSSFRow row = sheet.getRow(rowNumber);
		List<String> values = new ArrayList<>();
		int cols = row.getLastCellNum();
		for(int i=0;i<=cols;i++) {
			values.add(row.getCell(i).toString());
		}
		return values;
		
	}
	
	public static List<String> getColumnValues(int colNumber){
		int rowCount = sheet.getLastRowNum();
		List<String> values = new ArrayList<>();
		for(int i=0;i<=rowCount;i++) {
			XSSFRow row = sheet.getRow(i);
			values.add(row.getCell(colNumber).toString());
		}
		return values;
		
	}
	
	public static Map<String,String> getRowDataWithColumnValue(int colNumber,String value){
		int rowCount = sheet.getLastRowNum();
		Map<String,String> map = new HashMap<>();
		int index = 0;
		int headerRow = 0;
		for(int i=0;i<=rowCount;i++) {
			XSSFRow row = sheet.getRow(i);
			if((row.getCell(colNumber).toString()).equals(value)) {
				index=i;
			}
		}
		XSSFRow headers = sheet.getRow(headerRow);
		XSSFRow row = sheet.getRow(index);
		for(int i=0;i<=row.getLastCellNum();i++) {
			map.put(headers.getCell(i).toString(), row.getCell(i).toString());
		}
		return map;
		
		
	}
	
	
	
	

}
