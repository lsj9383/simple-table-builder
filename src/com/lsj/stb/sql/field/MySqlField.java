package com.lsj.stb.sql.field;

public class MySqlField extends AbstractSqlField implements SqlField{
	
	protected MySqlField(String name, String chineseName, String type, Integer length, String desc) {
		super(name, chineseName, type, length, desc);
	}

	@Override
	public String createSql(){
		StringBuilder sqlsb = new StringBuilder();
		if(length != -1){
			sqlsb.append(String.format("%s %s(%d) ", name, type, length));
		}else{
			sqlsb.append(String.format("%s %s ", name, type));
		}
		if(isPrimaryKey){sqlsb.append(" primary key ");}
		if(isAutoIncrement){sqlsb.append(" auto_increment ");}
		if(isNotNull){sqlsb.append(" not null ");}
		
		return sqlsb.toString();
	}
	
	static public class Builder extends SqlFieldBuilder{
		@Override
		public SqlField doBuild(String name, String chineseName, String type, Integer length, String desc) {
			return new MySqlField(name, chineseName, type, length, desc);
		}
	}
}
