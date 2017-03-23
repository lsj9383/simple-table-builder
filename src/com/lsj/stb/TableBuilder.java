package com.lsj.stb;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;
import com.lsj.stb.validate.Access;

public interface TableBuilder {
	Map<Integer, SqlBuildingException> build(File file, Access access) throws SqlBuilderException;
	List<String> buildSqls(File file, Map<Integer, SqlBuildingException> mapExceptions) throws SqlBuilderException;
	
	enum SqlType{
		SqlServer,
		MySql
	}
}
