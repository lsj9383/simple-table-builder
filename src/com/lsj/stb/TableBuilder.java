package com.lsj.stb;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.structure.Table;

public interface TableBuilder {
	List<String> buildsqls(File file, Map<Integer, SqlBuildingException> mapExceptions) throws SqlBuilderException;
	List<Table> loadTable(File file) throws SqlBuilderException;
	enum SqlType{
		SqlServer,
		MySql
	}
}
