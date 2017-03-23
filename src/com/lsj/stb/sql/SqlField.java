package com.lsj.stb.sql;

import java.util.HashMap;
import java.util.Map;

public class SqlField {
	
	private String name;
	private String chineseName;
	private String type;
	private Integer length;
	private String desc;
	private Boolean isPrimaryKey = false;
	private Boolean isAutoIncrement = false;
	
	private SqlField(String name, String chineseName, String type, Integer length, String desc){
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
		
	}
	
	public String getName() {
		return name;
	}

	public String getChineseName() {
		return chineseName;
	}

	public String getType() {
		return type;
	}

	public Integer getLength() {
		return length;
	}

	public String getDesc() {
		return desc;
	}

	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public Boolean getIsAutoIncrement() {
		return isAutoIncrement;
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
	
	static public class Builder{
		private String name;
		private String chineseName;
		private String type;
		private String length;
		private String desc;
		
		public SqlField build(){
			Integer len = -1;
			try{
				len = (int)Double.parseDouble(length);
			}catch(Exception e){len = -1;}
			
			return new SqlField(name, chineseName, type, len, desc);	
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setChineseName(String chineseName) {
			this.chineseName = chineseName;
		}
		public void setType(String type) {
			this.type = type;
		}
		public void setLength(String length) {
			this.length = length;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
