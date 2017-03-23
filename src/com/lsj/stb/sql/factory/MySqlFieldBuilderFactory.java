package com.lsj.stb.sql.factory;

import com.lsj.stb.sql.field.SqlFieldBuilder;
import com.lsj.stb.sql.field.SqlServerField;

public class MySqlFieldBuilderFactory implements SqlFieldBuilderFactory{

	@Override
	public SqlFieldBuilder getSqlFieldBuilder() {
		return new SqlServerField.Builder();
	}

}
