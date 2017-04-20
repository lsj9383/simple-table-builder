package com.lsj.stb;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;

public interface SqlTableBuilder {
	List<String> buildsqls(File file, Map<Integer, SqlBuildingException> mapExceptions) throws SqlBuilderException;
	enum SqlType{
		SqlServer,
		MySql
	}
}
