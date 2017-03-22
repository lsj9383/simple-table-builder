package com.lsj.stb;

public abstract class AbstractTableBuilder implements TableBuilder{

	public final String url;
	
	public AbstractTableBuilder(String url){
		this.url = url;
	}
	
	@Override
	public void build() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		//注册驱动
		}catch(Exception e){
			
		}
	}
	
	abstract void doBuild();
	
}
