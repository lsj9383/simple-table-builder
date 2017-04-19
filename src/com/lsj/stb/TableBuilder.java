package com.lsj.stb;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;

public interface TableBuilder {
	List<String> buildsqls(File file, Map<Integer, SqlBuildingException> mapExceptions) throws SqlBuilderException;
	List<String> buildFormats(File file, Format format) throws SqlBuilderException, SqlBuildingException;
	
	enum SqlType{
		SqlServer,
		MySql
	}
}
