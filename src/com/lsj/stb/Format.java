package com.lsj.stb;

import com.lsj.stb.sql.field.SqlFieldBuilder;

public interface Format {
	String output(int index, SqlFieldBuilder builder);
}
