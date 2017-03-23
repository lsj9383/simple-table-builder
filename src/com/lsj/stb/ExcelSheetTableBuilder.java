package com.lsj.stb;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lsj.stb.sql.SqlField;
import com.lsj.stb.sql.SqlTable;

public class ExcelSheetTableBuilder extends AbstractTableBuilder implements TableBuilder{

	public ExcelSheetTableBuilder(String url, String username, String password) throws ClassNotFoundException, SQLException {
		super(url, username, password);
	}

	@Override
	public List<SqlTable> buildSql(File file){
		List<SqlTable> sqlTables = new ArrayList<>();
		try{
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			for(int sheetNumber=0; sheetNumber<workbook.getNumberOfSheets(); sheetNumber++){
				XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
				if(sheet==null || sheet.getLastRowNum()==0){continue;}
				
				SqlTable table = new SqlTable(sheet.getSheetName());
				List<String> columns = loadColumnName(sheet.getRow(sheet.getFirstRowNum()));			//记录列名字顺序
				for(int rownum=sheet.getFirstRowNum()+1; rownum<sheet.getLastRowNum(); rownum++){
					SqlField.Builder builder = new SqlField.Builder();
					for(int cellnum=0; cellnum<sheet.getRow(rownum).getLastCellNum(); cellnum++){
						loadSqlFieldBuilder(builder, columns.get(cellnum), sheet.getRow(rownum).getCell(cellnum).toString());
					}
					table.sqlFieldBuilders.add(builder);		//在table缓存每个字段的创建者, 目的是将builder在构建中可能会出的问题延迟到外面
				}
				sqlTables.add(table);
			}
			workbook.close();
			return sqlTables;
		}catch(Exception e){return null;}
	}
	
	private void loadSqlFieldBuilder(SqlField.Builder builder, String colname, String content){
		String propertyName = col2pro.get(colname);
		switch(propertyName){
			case "chineseName": builder.setChineseName(content);break;
			case "name": builder.setName(content);break;
			case "type": builder.setType(content);break;
			case "length": builder.setLength(content);break;
			case "desc": builder.setDesc(content);break;
			default:break;
		}
	} 
	
	private List<String> loadColumnName(XSSFRow firstRow){
		List<String> columns = new ArrayList<>();
		for(Cell cell : firstRow){
			columns.add(cell.toString());
		}
		return columns;
	}

}
