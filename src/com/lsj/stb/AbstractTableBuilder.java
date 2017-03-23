package com.lsj.stb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.SqlTable;

public abstract class AbstractTableBuilder extends AbstractStaticInformationTableBuilder implements TableBuilder{

	public final String url;
	public final String username;
	public final String password;
	private final Connection conn;
	private final Statement stmt;
	private boolean isClose = false;
	
	public AbstractTableBuilder(String url, String username, String password) throws ClassNotFoundException, SQLException{
		this.url = url;
		this.username=username;
		this.password=password;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	//注册驱动, 有待解耦
		conn = DriverManager.getConnection(url, username, password);	//建立连接
		stmt = conn.createStatement();
	}
	
	@Override
	public Map<Integer, SqlBuildingException> build(File file) throws SqlBuilderException {
		Map<Integer, SqlBuildingException> exceptionMap = new HashMap<>();
		if(isClose){throw new SqlBuilderException("builder is closed");}		//创建者已经关闭
		if(!file.exists()){throw new SqlBuilderException("file is not exist");}	//文件不存在
		List<SqlTable> sqlTables = buildSql(file);
		if(sqlTables == null){throw new SqlBuilderException("sqls is null");}	//sql建表语句为空
		
		for(int index=0; index<sqlTables.size(); index++){
			try{
				int result = stmt.executeUpdate(sqlTables.get(index).createTalbeSql());
				System.out.println(result);
			}catch(Exception createTableException){
				exceptionMap.put(index, new SqlBuildingException(createTableException.getMessage()));
			}
		}
		
		return exceptionMap;
	}
	
	@Override
	public boolean isClose() {
		return isClose;
	}
	
	@Override
	public void close() {
		isClose = true;
		try {stmt.close(); conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	abstract List<SqlTable> buildSql(File file);
	
}
