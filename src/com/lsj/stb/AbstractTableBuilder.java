package com.lsj.stb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.SqlTable;
import com.lsj.stb.sql.factory.SqlFieldBuilderFactory;
import com.lsj.stb.sql.factory.SqlServerFieldBuilderFactory;
import com.lsj.stb.validate.Access;


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
		Class.forName(driverMap.get(sqlType));							//注册驱动
	}
	
	@Override
	public List<String> buildSqls(File file,  Map<Integer, SqlBuildingException> exceptionMap) throws SqlBuilderException {
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
	
	@Override
	public Map<Integer, SqlBuildingException> build(File file, Access access) throws SqlBuilderException{
		Map<Integer, SqlBuildingException> exceptionMap = new HashMap<>();
		if(!file.exists()){throw new SqlBuilderException("file is not exist");}	//文件不存在
		List<SqlTable> sqlTables = buildSql(file);
		if(sqlTables == null){throw new SqlBuilderException("sqls is null");}	//sql建表语句为空
		
		try{
			Connection conn = DriverManager.getConnection(access.url, access.username, access.password);	//建立连接
			Statement stmt = conn.createStatement();
			
			for(int index=0; index<sqlTables.size(); index++){
				try{
					stmt.executeUpdate(sqlTables.get(index).createTalbeSql());
				}catch(Exception createTableException){
					exceptionMap.put(index, new SqlBuildingException(createTableException.getMessage()));
				}
			}
			
			stmt.close();
			conn.close();	
		}catch(Exception e){
			throw new SqlBuilderException(e.getMessage());
		}
		
		return exceptionMap;
	}
		
	abstract List<SqlTable> buildSql(File file) throws SqlBuilderException;
}
