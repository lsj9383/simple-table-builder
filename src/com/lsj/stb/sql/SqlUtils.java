package com.lsj.stb.sql;

public class SqlUtils {
	static public String sqlName2JavaName(String sqlName){
		String[] parts = sqlName.split("_");
		StringBuilder sb = new StringBuilder();
		sb.append(parts[0]);
		for(int i=1; i<parts.length; i++){
			sb.append(parts[i].substring(0, 1).toUpperCase()+parts[i].substring(1));
		}
		return sb.toString();
	}
}
