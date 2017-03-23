package com.lsj.stb;

import java.io.File;
import java.util.List;

import com.lsj.stb.sql.SqlTable;

public class WordTableBuilder extends AbstractTableBuilder  implements TableBuilder{

	public WordTableBuilder(SqlType sqlType) throws ClassNotFoundException {
		super(sqlType);
	}

	@Override
	public List<SqlTable> buildSql(File file) {
		return null;
	}
}
