package demo;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.SqlTableBuilder;
import com.lsj.stb.exception.SqlBuilderException;

public class CreatTableTest {

	@Test
	public void test() throws ClassNotFoundException, SqlBuilderException {
		SqlTableBuilder stb = new ExcelSheetTableBuilder(SqlTableBuilder.SqlType.SqlServer);		//创建支持SqlServer建表的SqlTableBuilder
		List<String> buildSqls = stb.buildsqls(new File("nb.xlsx"), new HashMap<>());				//获得对应excel中所有sheet的建表语句，hashMap用于记录错误
		System.out.println(buildSqls.get(0));
	}

}
