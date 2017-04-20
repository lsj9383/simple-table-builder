package com.lsj.stb.structure;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TableUtils {
	public static List<Table> createTableByExcel(File file) throws Exception{
		List<Table> tables = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		for(int sheetNumber=0; sheetNumber<workbook.getNumberOfSheets(); sheetNumber++){
			XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
			if(sheet==null || sheet.getLastRowNum()==0){continue;}
			
			List<String> keys = loadKeys(sheet.getRow(sheet.getFirstRowNum()));			//记录列名字顺序
			List<List<String>> lines = new ArrayList<>();
			for(int rownum=sheet.getFirstRowNum()+1; rownum<=sheet.getLastRowNum(); rownum++){
				List<String> line = new ArrayList<>();
				for(int cellnum=0; cellnum<sheet.getRow(rownum).getLastCellNum(); cellnum++){
					String content = getRowCellContent(sheet, rownum, cellnum);
					line.add(content);
				}
				lines.add(line);
			}
			Table table = new SimpleTable(sheet.getSheetName(), keys.toArray(new String[keys.size()]));
			for(List<String> line : lines){
				table.addLine(line.toArray(new String[line.size()]));
			}
			tables.add(table);
		}
		workbook.close();
		
		return tables;
	}
	
	static private List<String> loadKeys(XSSFRow firstRow){
		List<String> columns = new ArrayList<>();
		for(Cell cell : firstRow){
			columns.add(cell.toString());
		}
		return columns;
	}
	
	static private String getRowCellContent(XSSFSheet sheet, int row, int cell){
		XSSFRow ro = sheet.getRow(row);
		if(ro == null){return null;}
		XSSFCell cel = ro.getCell(cell);
		if(cel == null){return null;}
		
		return cel.toString();
	}
}
