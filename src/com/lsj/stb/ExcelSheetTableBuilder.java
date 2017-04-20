package com.lsj.stb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.structure.Table;
import com.lsj.stb.structure.TableUtils;

public class ExcelSheetTableBuilder extends AbstractTableBuilder implements TableBuilder{

	public ExcelSheetTableBuilder(SqlType sqlType) throws ClassNotFoundException {
		super(sqlType);
	}

	@Override
	public List<Table> loadTable(File file) throws SqlBuilderException{
		List<Table> tables = new ArrayList<>();
		try{
			tables = TableUtils.createTableByExcel(file);
		}catch(Exception e){
			throw new SqlBuilderException(e.getMessage());
		}
		
		return tables;
	}
}
