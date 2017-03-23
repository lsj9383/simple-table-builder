package com.lsj.stb;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.lsj.stb.sql.SqlTable;

public class WordTableBuilder extends AbstractTableBuilder  implements TableBuilder{

	public WordTableBuilder(String url, String username, String password) throws ClassNotFoundException, SQLException {
		super(url, username, password);
	}

	@Override
	public List<SqlTable> buildSql(File file) {
		return null;
	}
}
