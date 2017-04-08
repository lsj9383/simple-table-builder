package demo;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lsj.stb.ExcelSheetTableBuilder;
import com.lsj.stb.TableBuilder;
import com.lsj.stb.exception.SqlBuilderException;
import com.lsj.stb.exception.SqlBuildingException;

public class Main {
	
	static void test1() throws ClassNotFoundException, SqlBuilderException{
		TableBuilder tb = new ExcelSheetTableBuilder(TableBuilder.SqlType.MySql);
		Map<Integer, SqlBuildingException> map = new HashMap<>();
		List<String> buildSqls = tb.buildsqls(new File("C:/Users/lu/Desktop/Temporary/nb.xlsx"), map);
		for(String sql : buildSqls){
			System.out.println(sql);
			System.out.println("*******************************");
		}
		for(Entry<Integer, SqlBuildingException> entry : map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue().getMessage());
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, SqlBuilderException{
		test1();
		System.out.println("done!");
	}
}
