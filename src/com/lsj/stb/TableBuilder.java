package com.lsj.stb;

import java.io.File;
import java.util.Map;

import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;

public interface TableBuilder {
	Map<Integer, SqlBuildingException> build(File file) throws SqlBuilderException;
	void close();
	boolean isClose();
}
