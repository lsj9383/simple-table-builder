package com.lsj.stb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.SqlTable;
import com.lsj.stb.sql.factory.MySqlFieldBuilderFactory;
import com.lsj.stb.sql.factory.SqlFieldBuilderFactory;
import com.lsj.stb.sql.factory.SqlServerFieldBuilderFactory;
import com.lsj.stb.sql.field.SqlFieldBuilder;
import com.lsj.stb.structure.Line;
import com.lsj.stb.structure.Table;

public abstract class AbstractTableBuilder extends AbstractStaticInformationTableBuilder implements TableBuilder{

	protected final SqlType sqlType;
	protected SqlFieldBuilderFactory fbfactory;
	
	public AbstractTableBuilder(SqlType sqlType) throws ClassNotFoundException{
		this.sqlType = sqlType;
		switch(sqlType){
			case SqlServer: fbfactory = new SqlServerFieldBuilderFactory(); break;
			case MySql: fbfactory = new MySqlFieldBuilderFactory(); break;
			default : fbfactory = null; break;
		}
	}
	
	@Override
	public List<String> buildsqls(File file,  Map<Integer, SqlBuildingException> exceptionMap) throws SqlBuilderException {
		if(!file.exists()){throw new SqlBuilderException("office file is not exist");}	//文件不存在
		List<Table> tables = loadTable(file);
		List<SqlTable> sqlTables = new ArrayList<>();
		for(Table table : tables){
			SqlTable sqlTable = new SqlTable(table.getName());
			for(Line line : table){
				SqlFieldBuilder builder = fbfactory.getSqlFieldBuilder();
				String[] keys = line.getKeys();
				for(String key: keys){
					loadSqlFieldBuilder(builder, key, line.get(key));	
				}
				sqlTable.sqlFieldBuilders.add(builder);
			}
			sqlTables.add(sqlTable);
		}
		
		List<String> sqls = new ArrayList<>();
		for(int index=0; index<sqlTables.size(); index++){
			try{
				sqls.add(sqlTables.get(index).createTalbeSql());
			}catch(Exception createTableException){
				exceptionMap.put(index, new SqlBuildingException(createTableException.getMessage()));
			}
		}
		return sqls;
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
	
	abstract public List<Table> loadTable(File file) throws SqlBuilderException;
}
