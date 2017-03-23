package com.lsj.stb.sql.field;

import java.util.HashMap;
import java.util.Map;

/**
 * sql字段抽象类，描述sql字段的所有信息。
 * 不同的数据库在创建sql语句是略有不同，这些不同点由子类来描述。
 * 
 * @author lu
 *
 */
public abstract class AbstractSqlField implements SqlField{
	protected String name;
	protected String chineseName;
	protected String type;
	protected Integer length;
	protected String desc;
	protected Boolean isPrimaryKey = false;
	protected Boolean isAutoIncrement = false;
	protected Boolean isNotNull = false;
	
	protected AbstractSqlField(String name, String chineseName, String type, Integer length, String desc){
		this.name = name;
		this.chineseName = chineseName;
		this.type = type;
		this.length = length;
		this.desc = desc;
		
		if(desc.contains("主键")){isPrimaryKey = true;}
		if(desc.contains("primarykey")){isPrimaryKey = true;}
		if(desc.contains("PRIMARYKEY")){isPrimaryKey = true;}
		if(desc.contains("primary key")){isPrimaryKey = true;}
		if(desc.contains("PRIMARY KEY")){isPrimaryKey = true;}
		
		if(desc.contains("自增")){isAutoIncrement = true;}
		if(desc.contains("AUTOINCREMENT")){isAutoIncrement = true;}
		if(desc.contains("autoincrement")){isAutoIncrement = true;}
		if(desc.contains("AUTO INCREMENT")){isAutoIncrement = true;}
		if(desc.contains("auto increment")){isAutoIncrement = true;}
		
		if(desc.contains("非空")){isNotNull = true;}
		if(desc.contains("不为空")){isNotNull = true;}
		if(desc.contains("notnull")){isNotNull = true;}
		if(desc.contains("NOTNULL")){isNotNull = true;}
		if(desc.contains("not null")){isNotNull = true;}
		if(desc.contains("NOT NULL")){isNotNull = true;}
	}
	
	@Override
	public String toString() {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("chinesesName", chineseName);
		map.put("type", type);
		map.put("length", length.toString());
		map.put("desc", desc);
		map.put("isPrimaryKey", isPrimaryKey.toString());
		map.put("isAutoIncrement", isAutoIncrement.toString());
		return super.toString();
	}
}
