package com.lsj.stb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.SqlTable;
import com.lsj.stb.sql.factory.SqlFieldBuilderFactory;
import com.lsj.stb.sql.factory.SqlServerFieldBuilderFactory;

public abstract class AbstractTableBuilder extends AbstractStaticInformationTableBuilder implements TableBuilder{

	protected final SqlType sqlType;
	protected SqlFieldBuilderFactory fbfactory;
	
	public AbstractTableBuilder(SqlType sqlType) throws ClassNotFoundException{
		this.sqlType = sqlType;
		switch(sqlType){
			case SqlServer: fbfactory = new SqlServerFieldBuilderFactory(); break;
			case MySql: fbfactory = new SqlServerFieldBuilderFactory(); break;
			default : fbfactory = null; break;
		}
	}
	
	@Override
	public List<String> buildsqls(File file,  Map<Integer, SqlBuildingException> exceptionMap) throws SqlBuilderException {
		if(!file.exists()){throw new SqlBuilderException("office file is not exist");}	//文件不存在
		List<SqlTable> sqlTables = buildSql(file);
		if(sqlTables == null){throw new SqlBuilderException("sqls is null");}	//sql建表语句为空
		
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
	
	abstract List<SqlTable> buildSql(File file) throws SqlBuilderException;
}
