package com.lsj.stb.sql.field;

import com.lsj.stb.exception.SqlBuildingException;

/**
 * 由于sql字段的属性较多，因此特意使用一个创建者来进行sql字段类的创建。
 * 具体创建哪种sql的字段，由子类决定。
 * 
 * @author lu
 *
 */
public abstract class SqlFieldBuilder {
	private String name;
	private String chineseName;
	private String type;
	private String length;
	private String desc;
	
	public String getName() {
		return name;
	}

	public String getChineseName() {
		return chineseName;
	}

	public String getType() {
		return type;
	}

	public String getLength() {
		return length;
	}

	public String getDesc() {
		return desc;
	}

	public abstract SqlField doBuild(String name, String chineseName, String type, Integer length, String desc);
	
	public SqlField build() throws SqlBuildingException{
		Integer len = -1;
		try{
			if(length.length() != 0){
				len = (int)Double.parseDouble(length);	
			}
			return doBuild(name, chineseName, type, len, desc);
		}catch(Exception e){
			throw new SqlBuildingException(String.format("field<%s, %s, %s, %s, %s> parse error", chineseName, name, type, length, desc));
		}
	}
	public SqlFieldBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public SqlFieldBuilder setChineseName(String chineseName) {
		this.chineseName = chineseName;
		return this;
	}
	public SqlFieldBuilder setType(String type) {
		this.type = type;
		return this;
	}
	public SqlFieldBuilder setLength(String length) {
		this.length = length;
		return this;
	}
	public SqlFieldBuilder setDesc(String desc) {
		this.desc = desc;
		return this;
	}
}
