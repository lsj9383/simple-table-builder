package com.lsj.stb;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.sql.SqlTable;
import com.lsj.stb.sql.field.SqlFieldBuilder;

public class ExcelSheetTableBuilder extends AbstractTableBuilder implements TableBuilder{

	public ExcelSheetTableBuilder(SqlType sqlType) throws ClassNotFoundException {
		super(sqlType);
	}

	@Override
	public List<SqlTable> buildSql(File file) throws SqlBuilderException{
		List<SqlTable> sqlTables = new ArrayList<>();
		try{
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			for(int sheetNumber=0; sheetNumber<workbook.getNumberOfSheets(); sheetNumber++){
				XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
				if(sheet==null || sheet.getLastRowNum()==0){continue;}
				
				SqlTable table = new SqlTable(sheet.getSheetName());
				List<String> columns = loadColumnName(sheet.getRow(sheet.getFirstRowNum()));			//记录列名字顺序
				for(int rownum=sheet.getFirstRowNum()+1; rownum<=sheet.getLastRowNum(); rownum++){
					SqlFieldBuilder builder = fbfactory.getSqlFieldBuilder();
					for(int cellnum=0; cellnum<sheet.getRow(rownum).getLastCellNum(); cellnum++){
						loadSqlFieldBuilder(builder, columns.get(cellnum), sheet.getRow(rownum).getCell(cellnum).toString());
					}
					table.sqlFieldBuilders.add(builder);		//在table缓存每个字段的创建者, 目的是将builder在构建中可能会出的问题延迟到外面
				}
				sqlTables.add(table);
			}
			workbook.close();
			return sqlTables;
		}catch(Exception e){throw new SqlBuilderException(e.getMessage());}
	}
	
	private void loadSqlFieldBuilder(SqlFieldBuilder builder, String colname, String content) throws SqlBuilderException{
		String propertyName = col2pro.get(colname);
		if(propertyName == null){
			throw new SqlBuilderException("the column name <"+colname+"> cannot parse");
		}
		
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
