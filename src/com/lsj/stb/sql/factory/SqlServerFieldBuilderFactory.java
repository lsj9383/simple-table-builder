package com.lsj.stb.sql.factory;

import com.lsj.stb.sql.field.MySqlField;
import com.lsj.stb.sql.field.SqlFieldBuilder;

public class SqlServerFieldBuilderFactory implements SqlFieldBuilderFactory{

	@Override
	public SqlFieldBuilder getSqlFieldBuilder() {
		return new MySqlField.Builder();
	}

}
