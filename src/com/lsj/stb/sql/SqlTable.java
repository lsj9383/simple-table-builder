package com.lsj.stb.sql;

import java.util.ArrayList;
import java.util.List;

public class SqlTable {
	public final String tableName;
	public final List<SqlField> sqlFields = new ArrayList<>();
	public final List<SqlField.Builder> sqlFieldBuilders = new ArrayList<>();
	
	public SqlTable(String tableName){
		this.tableName = tableName;
	}
	
	public String createTalbeSql(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(" create table %s ( ", tableName));
		for(SqlField sqlField : sqlFields){
			String line = null;
			if(sqlField.getLength() != -1){
				line = String.format( "%s %s(%d) ", sqlField.getName(), sqlField.getType(), sqlField.getLength());
			}else{
				line = String.format(" %s %s ", sqlField.getName(), sqlField.getType());
			}
			sb.append(line);
			if(sqlField.getIsPrimaryKey()){sb.append(" primary key ");}
			if(sqlField.getIsPrimaryKey()){sb.append(" identity ");}
			sb.append(" ,\n ");
		}
		sb.deleteCharAt(sb.length()-1);	//删除最后一个空格
		sb.deleteCharAt(sb.length()-1);	//删除最后一个回车
		sb.deleteCharAt(sb.length()-1);	//删除最后一个逗号
		sb.append(")");
		return sb.toString();
	}
}
