package com.lsj.stb.sql;

import java.util.ArrayList;
import java.util.List;

import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.sql.field.SqlFieldBuilder;
import com.lsj.stb.sql.field.SqlServerField;

public class SqlTable {
	public final String tableName;
	public final List<SqlServerField> sqlFields = new ArrayList<>();
	public final List<SqlFieldBuilder> sqlFieldBuilders = new ArrayList<>();
	
	public SqlTable(String tableName){
		this.tableName = tableName;
	}
	
	public String createTalbeSql() throws SqlBuildingException{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("create table %s (\n", tableName));
		for(SqlFieldBuilder builder : sqlFieldBuilders){
			sb.append(builder.build().createSql()+",\n");
		}
		sb.deleteCharAt(sb.length()-1);	//删除最后一个空格
		sb.deleteCharAt(sb.length()-1);	//删除最后一个回车
		sb.deleteCharAt(sb.length()-1);	//删除最后一个逗号
		sb.append(")");
		return sb.toString();
	}
}
